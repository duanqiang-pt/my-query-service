package com.zhongsheng.component.cache;

import java.io.Serializable;
import java.util.List;

import com.zhongsheng.component.where.Where;

/**
 * 用于缓存Where条件的key
 * @version 1.0
 */
public class WhereCacheKey<T> implements Serializable{

    private static final long serialVersionUID = -4577313390106306387L;
    
    private Where whereCondition;
    
    private List<T> handleData;

    public WhereCacheKey(Where whereCondition, List<T> handleData) {
        this.whereCondition = whereCondition;
        this.handleData = handleData;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handleData == null) ? 0 : handleData.hashCode());
		result = prime * result + ((whereCondition == null) ? 0 : whereCondition.hashCode());
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
		WhereCacheKey<?> other = (WhereCacheKey<?>) obj;
		if (handleData == null) {
			if (other.handleData != null)
				return false;
		} else if (!handleData.equals(other.handleData))
			return false;
		if (whereCondition == null) {
			if (other.whereCondition != null)
				return false;
		} else if (!whereCondition.equals(other.whereCondition))
			return false;
		return true;
	}
    
    
    
    

}
