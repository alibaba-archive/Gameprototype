package org.gameprototype.event;


/**
 * Created by zhoubo on 15-9-6.
 */

public class EventDrivenUtils
{
	private static class SingletonHolder
	{
		final static EventDrivenUtils instance = new EventDrivenUtils();
	}

	public static EventDrivenUtils getInstance()
	{
		return SingletonHolder.instance;
	}

	private EventDriving[] eventDrivings = new EventDriving[Runtime
			.getRuntime().availableProcessors()];

	private int drivingCount;

	private EventDrivenUtils()
	{
		drivingCount = Runtime.getRuntime().availableProcessors();

		eventDrivings = new EventDriving[drivingCount];

		for (int i = 0; i < drivingCount; i++)
		{
			eventDrivings[i] = new EventDriving(i);
		}
	}

	/**
	 * 添加事件监听器 默认是异步模式
	 * 
	 * @param type
	 *            事件类别(名称)
	 * @param listener
	 *            事件处理器
	 * @author EXvision
	 * @since 2012-6-7
	 */
	public void addListener(String type,
			@SuppressWarnings("rawtypes") IEventListener listener)
	{
		addListener(type, listener, false);
	}

	/**
	 * 添加事件监听器 可选模式
	 * 
	 * @param type
	 *            事件类别(名称)
	 * @param listener
	 *            事件监听器
	 * @param sync
	 *            同步标记
	 * @author EXvision
	 * @since 2012-6-7
	 */
	public void addListener(String type,
			@SuppressWarnings("rawtypes") IEventListener listener, boolean sync)
	{
		eventDrivings[abs(type.hashCode()) % drivingCount].addListener(type,
				listener, sync);
	}

	/**
	 * 抛出事件
	 * 
	 * @param event
	 *            事件
	 * @author EXvision
	 * @since 2012-6-7
	 */
	@SuppressWarnings(
	{ "rawtypes" })
	public void dispatchEvent(IEvent event)
	{
		eventDrivings[abs(event.getType().hashCode()) % drivingCount]
				.dispatchEvent(event);
	}

	/**
	 * 移除事件
	 * 
	 * @param type
	 *            事件类别(名称)
	 * @param listener
	 *            事件监听器
	 * @author EXvision
	 * @since 2012-6-7
	 */
	public void removeListener(String type,
			@SuppressWarnings("rawtypes") IEventListener listener)
	{
		removeListener(type, listener, false);
	}

	/**
	 * 移除事件<br>
	 * sync标记请和监听时<b>保持一致</b> 否则移除不了不要怪我哈哈
	 * 
	 * @param type
	 *            事件类别(名称)
	 * @param listener
	 *            事件监听器
	 * @param sync
	 *            同步标记
	 * @author EXvision
	 * @since 2012-6-7
	 */
	public void removeListener(String type,
			@SuppressWarnings("rawtypes") IEventListener listener, boolean sync)
	{
		eventDrivings[abs(type.hashCode()) % drivingCount].removeListener(type,
				listener, sync);
	}

	private int abs(int x)
	{
		return x < 0 ? -x : x;
	}
}
