package com.zhongsheng.component.where;

import java.util.ArrayList;
import java.util.List;

/**
 * 相当于SQL中的where子句的条件
 * 该类可以被扩展，所以字段为protected修饰
 * @version 1.0
 */
public class Where {
    
    
    /*
     * 查询条件集分组，组内部用or连接，集内部用and连接
     */
    protected List<OrItem> orCriteriaList;
    

    /**
     * 默认构造器
     */
    public Where() {
        
    }
    
    
    /**
     * 增加查询条件集
     * 
     * @param orCriteria
     */
    public void addOr(OrItem orCriteria) {
        //防止空指针
        if (orCriteriaList == null) {
            orCriteriaList = new ArrayList<>();
        }
        orCriteriaList.add(orCriteria);
    }
    
    /**
     * 生成并增加查询条件集
     * @return      生成的查询条件集
     */
    public OrItem addOr() {
        OrItem orCriteria = new OrItem();
        //防止空指针
        if (orCriteriaList == null) {
            orCriteriaList = new ArrayList<>();
        }
        orCriteriaList.add(orCriteria);
        return orCriteria;
    }
    
    /**
     * 生成查询条件集：
     *      如果之前没有其他条件集，则直接放入条件集进行组合；
     *      如果已经有其他条件集，则单纯返回
     * @return
     */
    public OrItem createOrCriteria() {
        OrItem orCriteria = new OrItem();
        if (orCriteriaList == null) {
            orCriteriaList = new ArrayList<>();
        }
        if (orCriteriaList.isEmpty()) {
            orCriteriaList.add(orCriteria);
        }
        return orCriteria;
    }
    
    /**
     * 清空条件
     */
    public void clear() {
        orCriteriaList = null;
    }
    
    
    
    
    
    //-----------getter and setter ----------------------
    public List<OrItem> getOrCriteriaList() {
        return orCriteriaList;
    }

    public void setOrCriteriaList(List<OrItem> orCriteriaList) {
        this.orCriteriaList = orCriteriaList;
    }
    

}
