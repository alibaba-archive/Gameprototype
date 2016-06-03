package org.gameprototype.basicdata;

import java.util.Map;

public interface IBasicDataInitial<T> {
	/**
	 * 初始化
	 */
	void init();
	/**
	 * 获取所有数据
	 * @return
	 */
	Map<Integer,T> getAll();
}
