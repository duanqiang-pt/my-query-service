package com.zhongsheng.component;

/**
 * 相当于SQL中的limit子句的条件
 * 这个类可以扩展继承，所以属性
 * 定义为protected
 * @version 1.0
 */
public class Limit {
	
	/*
	 * 起始偏移量
	 */
	protected int offset;
	
	/*
	 * 连续的大小
	 */
	protected int size;

	public int getOffset() {
		return offset;
	}

	public int getSize() {
		return size;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
