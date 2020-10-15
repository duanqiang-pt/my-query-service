package com.zhongsheng.strategy.impl;

import java.util.List;

import com.zhongsheng.component.Limit;
import com.zhongsheng.strategy.SqlLimitStrategy;

/**
 * limit条件默认策略类
 * @version 1.0
 */
public class LimitDefaultStrategy implements SqlLimitStrategy {

	@Override
	public <T> List<T> toHandleLimit(List<T> handleData, Limit limitCondition) {
		//如果limitCondition为null则表示不需要limit处理
		if(limitCondition==null){
			return handleData;
		}
		 //由于之前有过参数的校验，所以这里不需要关心空指针和数组下标越界问题
        int offset = limitCondition.getOffset();
		return handleData.subList(offset, offset+limitCondition.getSize());
	}
	
	

}
