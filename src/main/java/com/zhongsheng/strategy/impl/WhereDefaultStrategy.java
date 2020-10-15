package com.zhongsheng.strategy.impl;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import com.zhongsheng.component.where.Where;
import com.zhongsheng.exception.SqlQueryException;
import com.zhongsheng.executor.task.QueryTask;
import com.zhongsheng.strategy.SqlWhereStrategy;

/**
 * where条件默认策略类
 * @version 1.0
 */
public class WhereDefaultStrategy implements SqlWhereStrategy {
    
    /*
     * 线程池用于提高效率
     */
    private ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    

    @Override
    public <T> List<T> toHandleWhere(List<T> handleData, Where whereCondition){
        
        /*
         * 交给forkjoin去执行以便在handleData的大小超过5000时
         * 提高效率，这里需要注意forkjoin只能在where条件处理逻辑去使用，
         * 不能把后面的group by、order by和limit的逻辑都去使用forkjoin
         * 
         * 在QueryTask内部封装了对大于5000条的数据的处理临界值
         * 和处理逻辑，如果数据小于5000条
         */
        ForkJoinTask<List<T>> handledFuture = forkJoinPool.submit(new QueryTask<>(handleData,whereCondition));
        
        try {
            return handledFuture.get();
        } catch (Exception e) {
            throw new SqlQueryException(e);
        }
    }

}
