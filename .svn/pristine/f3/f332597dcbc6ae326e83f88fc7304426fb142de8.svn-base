package com.zhongsheng.component.orderby;

import java.util.Comparator;
import java.util.List;

import com.zhongsheng.component.enums.OrderByTypeEnum;
import com.zhongsheng.utils.ReflectUtils;

/**
 * 用于OrderBy功能的比较器
 * @version 1.0
 */
public class OrderByComparetor <T>  implements Comparator<WillOrderByObj<T>>{
    
    /*
     * order by的条件
     */
    private OrderBy orderByCondition;
    

    public OrderByComparetor(OrderBy orderByCondition) {
        this.orderByCondition = orderByCondition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(WillOrderByObj<T> a, WillOrderByObj<T> b) {
        //获取到排序详情的列表
        List<OrderByDetail> orderByDetailList = orderByCondition.getOrderByDetailList();
        
        for (int i = 0; i < orderByDetailList.size(); i++) {
         
            //之前已经校验过为空情况，这里直接使用
            String fieldName = orderByDetailList.get(i).getFieldName();
            //获取到排序类型（升序还是降序）
            OrderByTypeEnum orderByType = orderByDetailList.get(i).getOrderByType();
            
            //TODO 这里需要校验排序字段是否能够强转（也就是说排序字段是否支持相互比较）
            
            
            //获取到两个对象中第一个排序字段的值
            Comparable<Object> fieldValueForA =  (Comparable<Object>)ReflectUtils.getFieldValue(a.getTarget(), fieldName);
            Comparable<Object> fieldValueForB =  (Comparable<Object>)ReflectUtils.getFieldValue(b.getTarget(), fieldName);
            
            
            /*
             * 判断是否需要对第二个排序字段进行排序：
             *      如果第一个字段两个对象的值相等则需要对后续字段进行排序
             *      否则不需要
             */
            int compareResult = fieldValueForA.compareTo(fieldValueForB);
            if(compareResult != 0 || i==orderByDetailList.size() -1){
                //根据排序类型返回比较规则
                return orderByType==OrderByTypeEnum.ASC ?compareResult : -compareResult;
            }
            //以下为当前循环字段值相等的情况就继续循环下一个排序字段进行比较
        }
        
        return 0;
    }

}
