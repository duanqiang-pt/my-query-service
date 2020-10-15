package com.zhongsheng.strategy.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.zhongsheng.component.orderby.OrderBy;
import com.zhongsheng.component.orderby.OrderByComparetor;
import com.zhongsheng.component.orderby.WillOrderByObj;
import com.zhongsheng.strategy.SqlOrderByStrategy;

/**
 * order by条件默认策略类
 * @version 1.0
 */
public class OrderByDefaultStrategy implements SqlOrderByStrategy {

    @Override
    public <T> List<T> toHandleOrder(List<T> handleData, OrderBy orderByCondition) {
      
        //将待处理数据的List转换成可排序的对象的List
        List<T> resultList = handleData.stream().map(e ->{
            WillOrderByObj<T>   willOrderByObj = new WillOrderByObj<>();
            willOrderByObj.setTarget(e);
            willOrderByObj.setOrderByDetailList(orderByCondition.getOrderByDetailList());
            return willOrderByObj;
        })
        //对这个流进行排序（传入自定义的比较器）
        .sorted(new OrderByComparetor<>(orderByCondition))
        //再转换成原有对象List
        .map(WillOrderByObj::getTarget).collect(Collectors.toList());
        
        return resultList;
    }
    
    
    
    

}
