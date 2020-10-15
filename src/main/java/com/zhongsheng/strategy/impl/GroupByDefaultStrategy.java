package com.zhongsheng.strategy.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zhongsheng.component.GroupBy;
import com.zhongsheng.strategy.SqlGroupByStrategy;
import com.zhongsheng.utils.ReflectUtils;

/**
 * group by条件默认策略类
 * @version 1.0
 */
public class GroupByDefaultStrategy implements SqlGroupByStrategy {

	@Override
	public <T> Map<Object,List<T>> toHandleGroupBy(List<T> handleData, GroupBy groupBy) {
		
		if(groupBy==null){
			return null;
		}
		
		//获取到分组的字段名
		String groupByField = groupBy.getGroupByField();
		
		
		Map<Object, List<T>> collect = handleData.stream().collect(
            Collectors.groupingBy(
                    data -> ReflectUtils.getFieldValue(data, groupByField)));
		
		
		//执行分组
		return collect;
	}
	
	


}
