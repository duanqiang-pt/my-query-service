package com.zhongsheng.component.orderby;

import java.util.List;

/**
 * 相当于SQL中的order by子句的条件
 * @version 1.0
 */
public class OrderBy {
	
	/*
	 * 排序详情List
	 */
    private List<OrderByDetail> orderByDetailList;

    public List<OrderByDetail> getOrderByDetailList() {
        return orderByDetailList;
    }

    public void setOrderByDetailList(List<OrderByDetail> orderByDetailList) {
        this.orderByDetailList = orderByDetailList;
    }
    
	
}
