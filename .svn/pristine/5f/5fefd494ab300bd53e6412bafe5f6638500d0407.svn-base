package com.zhongsheng.result;

import java.util.List;
import java.util.Map;

/**
 * 查询处理的结果
 * @version 1.0
 */
public class QueryHandleResult<T> {
	
	/*
	 * 含有group by的查询结果
	 */
	private Map<Object, List<T>> hasGroupByRessultMap;
	
	/*
	 * 通用查询结果
	 */
	private List<T> listResult;


	public List<T> getListResult() {
		return listResult;
	}

	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}

    public Map<Object, List<T>> getHasGroupByRessultMap() {
        return hasGroupByRessultMap;
    }

    public void setHasGroupByRessultMap(Map<Object, List<T>> groupBySqlProcessResult) {
        this.hasGroupByRessultMap = groupBySqlProcessResult;
    }

}
