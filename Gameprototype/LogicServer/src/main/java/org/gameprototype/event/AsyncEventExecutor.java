package org.gameprototype.event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import org.apache.log4j.Logger;
import org.springframework.util.MultiValueMap;


/**
 * Created by zhoubo on 15-9-6.
 */

public class AsyncEventExecutor implements Runnable
{
	private Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	private BlockingQueue<IEvent> queue;

	@SuppressWarnings("rawtypes")
	private MultiValueMap<String, IEventListener> asyncMap;

	private final Lock r;

	@SuppressWarnings("rawtypes")
	public AsyncEventExecutor(BlockingQueue<IEvent> queue,
			MultiValueMap<String, IEventListener> asyncMap, Lock r)
	{
		super();
		this.queue = queue;
		this.asyncMap = asyncMap;
		this.r = r;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				IEvent event = queue.take();
				r.lock();
				try
				{
					if (asyncMap.containsKey(event.getType()))
					{
						for (IEventListener listener : asyncMap.get(event
								.getType()))
						{
							listener.handle(event);
							if (logger.isDebugEnabled())
							{
								logger.debug("Event[" + event.getType()
										+ "] was executed by ASYNC[" + listener
										+ "] CMPL.");
							}
						}
					}
				}
				catch (Throwable e)
				{
					logger.error("Async event executing exception: ", e);
				}
				finally
				{
					r.unlock();
				}
			}
			catch (Throwable e)
			{
				logger.fatal("Async event executing interrupted exception: ", e);
			}
		}
	}
}
