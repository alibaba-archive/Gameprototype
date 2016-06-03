package org.gameprototype.event;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by zhoubo on 15-9-6.
 */

public class EventDriving
{
	private Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	private MultiValueMap<String, IEventListener> syncMap = new LinkedMultiValueMap<String, IEventListener>();

	@SuppressWarnings("rawtypes")
	private MultiValueMap<String, IEventListener> asyncMap = new LinkedMultiValueMap<String, IEventListener>();

	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();

	@SuppressWarnings("rawtypes")
	private BlockingQueue<IEvent> asyncQueue = new DelayQueue<IEvent>();

	public EventDriving(int index)
	{
		AsyncEventExecutor executor = new AsyncEventExecutor(asyncQueue,
				asyncMap, r);
		Thread thread = new Thread(executor);
		thread.setName("Async event executor#" + index);
		thread.start();

		if (logger.isDebugEnabled())
		{
			logger.debug("Async event executor start! ID:" + index);
		}
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
		w.lock();
		try
		{
			if (sync)
			{
				syncMap.add(type, listener);
				if (logger.isDebugEnabled())
				{
					logger.debug("Event[" + type + "] add SYNC listener["
							+ listener + "] CMPL.");
				}
			}
			else
			{
				asyncMap.add(type, listener);
				if (logger.isDebugEnabled())
				{
					logger.debug("Event[" + type + "] add ASYNC listener["
							+ listener + "] CMPL.");
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Event[" + type + "] add " + (sync ? "SYNC" : "ASYNC")
					+ " listener[" + listener + "] failed: ", e);
		}
		finally
		{
			w.unlock();
		}
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
	{ "rawtypes", "unchecked" })
	public void dispatchEvent(IEvent event)
	{
		r.lock();
		try
		{
			boolean dispatched = false;
			List<IEventListener> listeners = syncMap.get(event.getType());
			if (listeners != null)
			{
				for (IEventListener listener : listeners)
				{
					listener.handle(event);
					if (logger.isDebugEnabled())
					{
						logger.debug("Event[" + event.getType()
								+ "] was executed by SYNC[" + listener
								+ "] CMPL.");
					}
					dispatched = true;
				}
			}

			if (asyncMap.containsKey(event.getType()))
			{
				asyncQueue.offer(event);
				dispatched = true;
			}
			if (!dispatched)
			{
				logger.warn("Event[" + event.getType()
						+ "] dispatch faild. No listener.");
			}
		}
		catch (Exception e)
		{
			logger.error("Event[" + event.getType() + "] dispatch faild: ", e);
		}
		finally
		{
			r.unlock();
		}
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
		r.lock();
		try
		{
			@SuppressWarnings("rawtypes")
			MultiValueMap<String, IEventListener> map = sync ? syncMap
					: asyncMap;
			if (map.containsKey(type))
			{
				r.unlock();
				w.lock();
				try
				{
					boolean success = map.get(type).remove(listener);
					if (success)
					{
						if (logger.isDebugEnabled())
						{
							logger.debug("Event[" + type + "] remove "
									+ (sync ? "SYNC" : "ASYNC") + " listener["
									+ listener + "] CPML.");
						}
					}
					else
					{
						logger.warn("Event[" + type
								+ "] remove listener faild. "
								+ (sync ? "SYNC" : "ASYNC") + " listener["
								+ listener + "] has not been registered!!!");
					}
				}
				finally
				{
					r.lock();
					w.unlock();
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Event[" + type + "] remove "
					+ (sync ? "SYNC" : "ASYNC") + " listener[" + listener
					+ "] failed: ", e);
		}
		finally
		{
			r.unlock();
		}
	}
}
