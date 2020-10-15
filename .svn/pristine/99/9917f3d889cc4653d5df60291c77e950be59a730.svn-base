package com.zhongsheng.strategy;

import java.util.List;

import com.zhongsheng.component.Limit;


/**
 * 关于limit功能的策略接口
 * @version 1.0
 */
public interface SqlLimitStrategy {
	
	/**
	 * 对于Limit查询功能的处理方法
	* @param handleData	需要处理的数据
	* @param limitCondition	limit条件
	* @return	处理后的结果
	 */
	 <T> List<T> toHandleLimit(List<T> handleData, Limit limitCondition);

}
