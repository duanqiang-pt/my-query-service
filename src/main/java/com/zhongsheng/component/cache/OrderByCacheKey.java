package com.zhongsheng.component.cache;

import java.io.Serializable;
import java.util.List;

import com.zhongsheng.component.orderby.OrderBy;


/**
 * 用于缓存OrderBy条件的key
 * @version 1.0
 */
public class OrderByCacheKey<T> implements Serializable{

    private static final long serialVersionUID = -4577313390106306387L;
    
    private OrderBy orderCondition;
    
    private List<T> handleData;

    public OrderByCacheKey(OrderBy orderCondition, List<T> handleData) {
        this.orderCondition = orderCondition;
        this.handleData = handleData;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handleData == null) ? 0 : handleData.hashCode());
		result = prime * result + ((orderCondition == null) ? 0 : orderCondition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderByCacheKey<?> other = (OrderByCacheKey<?>) obj;
		if (handleData == null) {
			if (other.handleData != null)
				return false;
		} else if (!handleData.equals(other.handleData))
			return false;
		if (orderCondition == null) {
			if (other.orderCondition != null)
				return false;
		} else if (!orderCondition.equals(other.orderCondition))
			return false;
		return true;
	}
    
    
    
    
}
