package com.zhongsheng.strategy;

import java.util.List;

import com.zhongsheng.component.orderby.OrderBy;

/**
 * 关于order by功能的策略接口
 * @version 1.0
 */
public interface SqlOrderByStrategy {
	
	
    /**
     *  对于OrderBy查询功能的处理方法
     * @param handleData    需要处理的数据
     * @param orderByCondition  order by条件
     * @return  处理后的结果
     */
    <T> List<T> toHandleOrder(List<T> handleData, OrderBy orderByCondition);
    
    
}
