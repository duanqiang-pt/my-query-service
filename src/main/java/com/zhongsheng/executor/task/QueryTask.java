package com.zhongsheng.executor.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;


import com.google.common.collect.Lists;
import com.zhongsheng.component.where.AndItem;
import com.zhongsheng.component.where.OrItem;
import com.zhongsheng.component.where.Where;
import com.zhongsheng.utils.ReflectUtils;


/**
 * 用于ForkJoin线程池处理的查询任务
 * @version 1.0
 */
public class QueryTask<T> extends RecursiveTask<List<T>>{

    private static final long serialVersionUID = 6246966022271750621L;

    /*
     * 每个任务能处理的临界值，本类中默认为5000
     */
    private static final int DEAL_WITH_THRESHOLD = 5000;
    
    
    /*
     * 开始的下标
     */
    private int start;
    
    /*
     * 结束的下标
     */
    private int end;
    
    /*
     * 需要处理的数据
     */
    private List<T>  handleData;
    
    /*
     * where条件
     */
    private Where where;
    
    
    public QueryTask(List<T>  handleData,Where where) {
        this.where = where;
        this.handleData=handleData;
        this.start=0;
        this.end=handleData.size()-1;
    }
    
    
    @Override
    protected List<T> compute() {
        //如果计算在自己任务的处理范围内就处理
        if((end-start)<=DEAL_WITH_THRESHOLD){
          
            //获取到内部的or条件List
            List<OrItem> orItemList = where.getOrCriteriaList();
            
            //初始化筛选后的结果
            Set<T> orProcessResultSet = new HashSet<>();
            
            
            //串行遍历执行or的条件筛选（这里因为在上一层已经进行了并行，所以这里不能再并行）
            orItemList.stream().forEach(orConditon ->{
                /*
                 * 执行每个or条件中的and条件，得到经过and条件筛选后的List
                 * 对所有的处理结果的List取去重后的并集
                 */
                orProcessResultSet.addAll(processAndCondition(handleData,orConditon));
            });
            
            //将Set转成List后赋值给数据对象
            List<T> resultList = Lists.newArrayList(orProcessResultSet);
            
            return  resultList;
            
        /*
         * 如果计算不在自己任务能处理的范围内，
         * 就进行拆分执行，（本例中拆分为2个任务）
         * 然后递归调用
         */
        }else{
            int middle=(start+end)/2;
            QueryTask<T> leftTask = new QueryTask<>(handleData.subList(start, middle),where);
            //这里必须传end+1，因为subList方法是含头不含尾的
            QueryTask<T> rightTask = new QueryTask<>(handleData.subList(middle, end+1),where);
            leftTask.fork();
            rightTask.fork();
            List<T> finalList = leftTask.join();
            finalList.addAll(rightTask.join());
            return finalList;
        }
    }

    
    /**
     * 处理and条件的筛选逻辑
     * @param willToProcessData 待筛选数据
     * @param orConditon    
     * @return
     */
    private  List<T> processAndCondition(List<T> willToProcessData, OrItem orConditon) {
        //这里需要另外创建一个集合去装and条件处理的结果，否则会导致多个or的集合被清空了
        List<T> andResultList = new ArrayList<>(willToProcessData);
        orConditon.getAndCriteriaList().forEach(andCondition->{
            switch(andCondition.getWhereType()){
                //对等于进行筛选
                case EQUAL_TO:
                   filterEqualTo(andResultList,andCondition);
                   break;
                //对不等于进行筛选
                case NOT_EQUAL_TO:
                    filterNotEqualTo(andResultList,andCondition);
                     break;
                //对in进行筛选
                case IN:
                     filterIn(andResultList,andCondition);
                     break;
                //对not in进行筛选   
                case NOT_IN:
                    //TODO
                //对like进行筛选
                case LIKE:
                    //TODO
                //对not like进行筛选
                case NOT_LIKE:
                //对between进行筛选
                case BETWEEN:
                /*
                 * TODO 
                 * 后面还有整个枚举的所有name字段，
                 * 包括is null、大于、小于、大于等于
                 * 等等等等
                 * 太多了短时间内没法完成
                 */
            default:
                
            }
        });
        return andResultList;
    }

    /**
     *  筛选出in字段值列表后的结果
     * @param willToProcessData     待处理数据
     * @param andCondition          匹配条件
     */
    private  void filterIn(List<T> willToProcessData, AndItem andCondition) {
            Set<T> resultSet = willToProcessData.stream()
                .filter(willEqualToElement -> {
                    @SuppressWarnings("unchecked")
                    List<T> valueList = (List<T>)andCondition.getValue();
                    return valueList.contains(
                            ReflectUtils.getFieldValue(willEqualToElement, andCondition.getFieldName()));
                })
                .collect(Collectors.toSet());
            //清理掉原有的垃圾数据
            willToProcessData.clear();
            willToProcessData.addAll(resultSet);
    }

    /**
     *  筛选出不等于字段值后的结果
     * @param willToProcessData     待处理数据
     * @param andCondition          匹配条件
     */
    private  void filterNotEqualTo(List<T> willToProcessData, AndItem andCondition) {
            Set<T> resultSet = willToProcessData.stream()
                .filter(willEqualToElement -> !Objects.equals(andCondition.getValue(),
                            ReflectUtils.getFieldValue(willEqualToElement, andCondition.getFieldName())))
                .collect(Collectors.toSet());
            //清理掉原有的垃圾数据
            willToProcessData.clear();
            willToProcessData.addAll(resultSet);
    }

    /**
     *  筛选出等于字段值后的结果
     * @param willToProcessData     待处理数据
     * @param andCondition          匹配条件
     */
    private  void filterEqualTo(List<T> willToProcessData,AndItem andCondition) {
            Set<T> resultSet = willToProcessData.stream()
                .filter(willEqualToElement -> Objects.equals(andCondition.getValue(),
                            ReflectUtils.getFieldValue(willEqualToElement, andCondition.getFieldName())))
                .collect(Collectors.toSet());
            //清理掉原有的垃圾数据
            willToProcessData.clear();
            willToProcessData.addAll(resultSet);
    }
    


}
