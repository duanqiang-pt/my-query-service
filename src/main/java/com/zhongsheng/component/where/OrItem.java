package com.zhongsheng.component.where;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zhongsheng.component.enums.WhereTypeEnum;
import com.zhongsheng.exception.SqlQueryException;


/**
 * 关于or关键字的条件
 * 
 * 动态查询条件，内部所有AndItem之间采用and结构，
 * 该类不会直接用到，应当通过Where对象访问
 * 
 * @version 1.0
 */
public class OrItem {
    
    
    /*
     * 查询条件集
     */
    protected List<AndItem> andCriteriaList;
    
    
    /**
     * 通过判断andCriteriaList是否为null来判断当前查询条件组是否生效
     * @return  是否生效
     */
    public boolean isValid() {
        return ! CollectionUtils.isEmpty(andCriteriaList);
    }
    
    
    /**
     * 向查询条件组中增加and条件
     * 
     * @param whereType     where条件匹配的类型
     * @param fieldName     要进行对比的字段名
     * @param value             要进行过滤的字段值
     */
    protected void addAndCriteria(WhereTypeEnum whereType,String fieldName,Object value) {
        if (whereType == null || fieldName == null) {
            throw new SqlQueryException("where type or fieldName cannot be null !");
        }
        if (andCriteriaList == null) {
            andCriteriaList = new ArrayList<>();
        }
        andCriteriaList.add(new AndItem(whereType,fieldName,value));
    }
    
    /**
     * 增加between双值查询条件
     *
     * @param whereType     where条件匹配的类型
     * @param fieldName     要进行对比的字段名
     * @param value             要进行过滤的字段值
     */
    protected void addCriterion(WhereTypeEnum whereType,
    				String fieldName,Object value,Object secondValue) {
        if (whereType == null || fieldName == null) {
            throw new SqlQueryException("where type or fieldName cannot be null !");
        }
        if (andCriteriaList == null) {
            andCriteriaList = new ArrayList<>();
        }
        andCriteriaList.add(new AndItem(whereType,fieldName,value,secondValue));
    }
    
    
    
    
    
    /**
     * 相当于：and fieldName = value
     * @param fieldName	字段名称
     * @param value	字段值
     * @return		添加条件集后的条件集分组（or分组）
     */
    public OrItem andEqualTo(String fieldName, Object value) {
    	addAndCriteria(WhereTypeEnum.EQUAL_TO, fieldName, value);
        return this;
    }
    
    
    /**
     * 相当于：and fieldName <> value
     * @param fieldName	字段名称
     * @param value	字段值
     * @return		添加条件集后的条件集分组（or分组）
     */
    public OrItem andNotEqualTo(String fieldName, Object value) {
    	addAndCriteria(WhereTypeEnum.NOT_EQUAL_TO, fieldName, value);
        return this;
    }
    
    
    /**
     * 相当于：and fieldName <> value
     * @param fieldName	字段名称
     * @param value	需要查看是否存在其中的List对象
     * @return		添加条件集后的条件集分组（or分组）
     */
    public OrItem andIn(String fieldName, List<Object> value) {
    	addAndCriteria(WhereTypeEnum.IN, fieldName, value);
        return this;
    }
    
    /**
     * 相当于：and fieldName between value and secondeValue
     * @param fieldName	字段名称
     * @param value	需要查看是否存在其中的List对象
     * @return		添加条件集后的条件集分组（or分组）
     */
    public OrItem andBetween(String fieldName, Object value,Object secondValue) {
    	addCriterion(WhereTypeEnum.BETWEEN, fieldName, value,secondValue);
        return this;
    }
    


    public List<AndItem> getAndCriteriaList() {
        return andCriteriaList;
    }


    public void setAndCriteriaList(List<AndItem> andCriteriaList) {
        this.andCriteriaList = andCriteriaList;
    }

    
    
    

}
