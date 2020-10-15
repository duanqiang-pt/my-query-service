package com.zhongsheng.component.cache;

import java.io.Serializable;
import java.util.List;

import com.zhongsheng.component.Limit;


/**
 * 用于缓存Limit条件的key
 * @version 1.0
 */
public class LimitCacheKey<T> implements Serializable{

    private static final long serialVersionUID = -4577313390106306387L;
    
    private Limit limitCondition;
    
    private List<T> handleData;

    public LimitCacheKey(Limit limitCondition, List<T> handleData) {
        this.limitCondition = limitCondition;
        this.handleData = handleData;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handleData == null) ? 0 : handleData.hashCode());
		result = prime * result + ((limitCondition == null) ? 0 : limitCondition.hashCode());
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
		LimitCacheKey<?> other = (LimitCacheKey<?>) obj;
		if (handleData == null) {
			if (other.handleData != null)
				return false;
		} else if (!handleData.equals(other.handleData))
			return false;
		if (limitCondition == null) {
			if (other.limitCondition != null)
				return false;
		} else if (!limitCondition.equals(other.limitCondition))
			return false;
		return true;
	}
    
    

    
}
