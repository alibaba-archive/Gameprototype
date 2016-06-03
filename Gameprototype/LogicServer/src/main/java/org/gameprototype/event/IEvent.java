package org.gameprototype.event;

import java.util.concurrent.Delayed;


/**
 * Created by zhoubo on 15-9-6.
 */

public interface IEvent<T> extends Delayed
{
	public static final long DELAY_TIME = 10000000000L;

	public String getType();

	public T getMessage();
}
