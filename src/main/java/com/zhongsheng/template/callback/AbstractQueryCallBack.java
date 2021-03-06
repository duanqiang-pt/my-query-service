package com.zhongsheng.template.callback;

import java.util.List;
import java.util.Map;

import com.zhongsheng.component.GroupBy;
import com.zhongsheng.component.Limit;
import com.zhongsheng.component.orderby.OrderBy;
import com.zhongsheng.component.where.Where;
import com.zhongsheng.strategy.SqlGroupByStrategy;
import com.zhongsheng.strategy.SqlLimitStrategy;
import com.zhongsheng.strategy.SqlOrderByStrategy;
import com.zhongsheng.strategy.SqlWhereStrategy;

/**
 * 查询业务处理回调抽象基础类。
 * 定义基础不包含任何附加能力
 * @version 1.0
 */
public abstract class AbstractQueryCallBack<R> {
    
    
    /**
     * 入参校验
     */
    public abstract void checkParams();
    
    
    /**
     * SQL中where子句的处理
     * @param willHandleData      待处理查询结果
     * @param where             相当于SQL中的where语句的功能对象
     * @param   sqlWhereStrategy    这里为了追求更高的扩展性这个参数是可动态配置的一个where条件的策略接口
     * @return  处理后的结果
     */
    public abstract List<R> whereSqlProcess(List<R> willHandleData,Where where,SqlWhereStrategy sqlWhereStrategy);
    
    
    /**
     * SQL中group by子句的处理
     * @param willHandleData  待处理查询结果
     * @param groupBy 	相当于SQL中的group by语句的功能对象
     * @param sqlGroupByStrategy    这里为了追求更高的扩展性这个参数是可动态配置的一个group by条件的策略接口
     * @return      处理后的结果
     */
    public abstract Map<Object,List<R>> groupBySqlProcess(List<R> willHandleData,GroupBy groupBy, SqlGroupByStrategy sqlGroupByStrategy);
    
    
    /**
     * SQL中order by子句的处理
     * @param willHandleData      	待处理查询结果
     * @param orderByObj			相当于SQL中的order by语句的功能对象
     * @param sqlOrderByStrategy    这里为了追求更高的扩展性这个参数是可动态配置的一个order by条件的策略接口
     * @return      处理后的结果
     */
    public abstract List<R> orderBySqlProcess(List<R> willHandleData,OrderBy orderByObj,SqlOrderByStrategy sqlOrderByStrategy);
    
    /**
     * SQL中limit子句的处理
     * @param willHandleData          待处理查询结果
     * @param	limitObj			相当于SQL中的where语句的功能对象
     * @param   sqlLimitStrategy    这里为了追求更高的扩展性这个参数是可动态配置的一个limit条件的策略接口
     * @return          处理后的结果
     */
    public abstract List<R> limitSqlProcess(List<R> willHandleData,Limit limitObj,SqlLimitStrategy sqlLimitStrategy);
    
    
    /**
     * 记录摘要日志
     * @param result    处理后的结果
     * @param queryContext  查询的上下文
     */
    public void recordLogDataModel(R result, List<R> data){
        
    }

}
