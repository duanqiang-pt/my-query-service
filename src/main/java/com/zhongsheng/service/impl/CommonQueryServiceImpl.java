package com.zhongsheng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zhongsheng.checker.LimitConditionChecker;
import com.zhongsheng.checker.OrderByConditionChecker;
import com.zhongsheng.checker.WhereConditionChecker;
import com.zhongsheng.component.GroupBy;
import com.zhongsheng.component.Limit;
import com.zhongsheng.component.SqlQueryCondition;
import com.zhongsheng.component.cache.GroupByCacheKey;
import com.zhongsheng.component.cache.LimitCacheKey;
import com.zhongsheng.component.cache.OrderByCacheKey;
import com.zhongsheng.component.cache.WhereCacheKey;
import com.zhongsheng.component.orderby.OrderBy;
import com.zhongsheng.component.where.Where;
import com.zhongsheng.exception.QueryParamIllgalException;
import com.zhongsheng.result.QueryHandleResult;
import com.zhongsheng.service.CommonQueryService;
import com.zhongsheng.strategy.SqlGroupByStrategy;
import com.zhongsheng.strategy.SqlLimitStrategy;
import com.zhongsheng.strategy.SqlOrderByStrategy;
import com.zhongsheng.strategy.SqlWhereStrategy;
import com.zhongsheng.template.SqlQueryTemplete;
import com.zhongsheng.template.callback.AbstractQueryCallBack;

/**
 * 用户查询服务实现类
 * @version 1.0
 */
@Service("commonQueryService")
public class CommonQueryServiceImpl implements CommonQueryService {
	
	/*
	 * 对于where条件的缓存：key为where条件后的内容，value为查询到的值
	 */
	private  Map<WhereCacheKey<?>, Object> whereCacheMap = new HashMap<>();
	
	
	/*
	 * 对于group by条件的缓存：key为group by条件后的内容，value为查询到的值
	 */
	private  Map<GroupByCacheKey<?>, Object> groupByCacheMap = new HashMap<>();
	
	
	/*
	 * 对于order by条件的缓存：key为order by条件后的内容，value为查询到的值
	 */
	private  Map<OrderByCacheKey<?>, Object> orderByCacheMap = new HashMap<>();
	
	
	/*
	 * 对于limit条件的缓存：key为limit条件后的内容，value为查询到的值
	 */
	private  Map<LimitCacheKey<?>, Object> limitCacheMap = new HashMap<>();
	
	
	@Override
	public <T>QueryHandleResult<T> query(List<T> selectAllData, SqlQueryCondition queryCondition) {
		
		return SqlQueryTemplete.doQueryProcess(selectAllData, new AbstractQueryCallBack<T>() {

            @Override
            public void checkParams() {
                //基础校验
                if(CollectionUtils.isEmpty(selectAllData)){
                	throw new QueryParamIllgalException("param selectAllData can not be empty !");
                }
                if(queryCondition == null){
                	throw new QueryParamIllgalException("param queryCondition can not be null !");
                }
                //校验where逻辑的入参
                WhereConditionChecker.checkWhereParams(queryCondition.getWhereObj());
                //校验group by逻辑的入参
                
                //校验order by逻辑的入参
                OrderByConditionChecker.checkOrderByParams(queryCondition.getOrderByObj());
                //校验limit逻辑的入参
                LimitConditionChecker.checkLimitParams(selectAllData, queryCondition.getLimitObj());
            }

            
            @Override
            public List<T> whereSqlProcess(List<T> willHandleData,Where where,SqlWhereStrategy sqlWhereStrategy) {
                
                //如果where为空说明不需要执行
                if(where==null){
                    return willHandleData;
                }
                
                
                WhereCacheKey<T> whereCacheKey = new WhereCacheKey<>(where, willHandleData);
                //从缓存中获取
                @SuppressWarnings("unchecked")
                List<T> cacheList = (List<T>)whereCacheMap.get(whereCacheKey);
                //缓存中有直接返回
                if(!CollectionUtils.isEmpty(cacheList)){
                    return cacheList;
                }
                
                List<T> resultList = sqlWhereStrategy.toHandleWhere(willHandleData, where);
              
                //将处理结果放入缓存中
                whereCacheMap.put(whereCacheKey, resultList);
                
                return resultList;
                
            }


            @Override
            public  Map<Object,List<T>> groupBySqlProcess(List<T> willHandleData,
            		GroupBy groupBy,SqlGroupByStrategy sqlGroupByStrategy) {
                
              
                //如果where为空说明不需要执行
                if(groupBy==null){
                    return null;
                }
                
                GroupByCacheKey<T> groupByCacheKey = new GroupByCacheKey<>(groupBy, willHandleData);
                
                //从缓存中获取
                @SuppressWarnings("unchecked")
                Map<Object,List<T>> cacheMap = (Map<Object,List<T>>)groupByCacheMap.get(groupByCacheKey);
                //缓存中有直接返回
                if(!CollectionUtils.isEmpty(cacheMap)){
                    return cacheMap;
                }
                
                Map<Object, List<T>> resultMap = 
                			sqlGroupByStrategy.toHandleGroupBy(willHandleData, groupBy);
                
                //将处理结果放入缓存中
            	groupByCacheMap.put(groupByCacheKey, resultMap);
            	
                return resultMap;
            }
            

			@Override
            public List<T> orderBySqlProcess(List<T> willHandleData,OrderBy orderBy,SqlOrderByStrategy sqlOrderByStrategy) {
			    //防止空指针，如果orderBy为null表示不需要排序直接返回
                if(orderBy ==null){
                    return willHandleData;
                }
                
                 OrderByCacheKey<T> orderByCacheKey = new OrderByCacheKey<>(orderBy, willHandleData);
                 
                 //从缓存中获取
                 @SuppressWarnings("unchecked")
                List<T> cacheList = (List<T>)orderByCacheMap.get(orderByCacheKey);
                 //缓存中有直接返回
                 if(!CollectionUtils.isEmpty(cacheList)){
                     return cacheList;
                 }
                
                 List<T> resultList = sqlOrderByStrategy.toHandleOrder(willHandleData, orderBy);
                 
                //将处理结果放入缓存中
                orderByCacheMap.put(orderByCacheKey, resultList);
                       
        	    return resultList;
		
            }

            @Override
            public List<T> limitSqlProcess(List<T> willHandleData,Limit limit,SqlLimitStrategy sqlLimitStrategy) {
                
                LimitCacheKey<T> limitCacheKey = new LimitCacheKey<>(limit, willHandleData);
                
                //从缓存中获取
                @SuppressWarnings("unchecked")
                List<T> cacheList = (List<T>)limitCacheMap.get(limitCacheKey);
                //缓存中有直接返回
                if(!CollectionUtils.isEmpty(cacheList)){
                    return cacheList;
                }
                
                List<T> resultList = sqlLimitStrategy.toHandleLimit(willHandleData, limit);
                
                //将处理结果放入缓存中
                limitCacheMap.put(limitCacheKey, resultList);
                
                return resultList;
            }

        },queryCondition);
	}
	
}
