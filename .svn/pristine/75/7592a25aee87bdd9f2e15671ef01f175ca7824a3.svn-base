package com.zhongsheng.strategy;

import java.util.List;
import java.util.Map;

import com.zhongsheng.component.GroupBy;

/**
 * 关于group by功能的策略接口
 * @version 1.0
 */
public interface SqlGroupByStrategy {
	
	/**
	 * 对于group by查询功能的处理方法
	* @param handleData
	* @param groupBy
	* @return
	 */
	<T> Map<Object,List<T>> toHandleGroupBy(List<T> handleData,GroupBy groupBy);

}
