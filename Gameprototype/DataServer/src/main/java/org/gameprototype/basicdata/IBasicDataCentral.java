package org.gameprototype.basicdata;

import java.util.Collection;

public interface IBasicDataCentral<T> {
	void init();
	Collection<T> getAll();
	T get(Integer code);
	boolean has(Integer code);
}
