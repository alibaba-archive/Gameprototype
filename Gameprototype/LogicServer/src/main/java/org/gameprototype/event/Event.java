package org.gameprototype.event;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * Created by zhoubo on 15-9-6.
 */

public class Event<T> implements IEvent<T>
{
	private String type;

	private T message;

	private long triger;

	public Event()
	{
		super();
		triger = DELAY_TIME + System.nanoTime();
	}

	public Event(String type)
	{
		this();
		this.type = type;
	}

	public Event(String type, T message)
	{
		this();
		this.type = type;
		this.message = message;
	}

	@Override
	public long getDelay(TimeUnit unit)
	{
		return triger - System.nanoTime();
	}

	@Override
	public int compareTo(Delayed o)
	{
		@SuppressWarnings("rawtypes")
		Event delayTask = (Event) o;
		return triger > delayTask.triger ? 1 : triger < delayTask.triger ? -1
				: 0;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the message
	 */
	public T getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(T message)
	{
		this.message = message;
	}

}
