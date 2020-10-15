package com.zhongsheng.checker;


import org.springframework.util.CollectionUtils;

import com.zhongsheng.component.orderby.OrderBy;
import com.zhongsheng.exception.QueryParamIllgalException;

/**
 *  关于OrderBy条件的检查者
 * @version 1.0
 */
public class OrderByConditionChecker {
    
    /**
     * 校验方法
     * @param orderByCondition    对于order by条件需要校验的入参
     */
    public static void  checkOrderByParams(OrderBy orderByCondition){
        
        //防止空指针，如果orderBy为null表示不需要排序直接返回
        if(orderByCondition==null){
            return;
        }
        
        //防止空指针和数组下标越界
        if(CollectionUtils.isEmpty(orderByCondition.getOrderByDetailList())){
            throw new QueryParamIllgalException("orderByDetailList is not empty !");
        }
        
    }

}
