package org.gameprototype.event;

import java.util.EventListener;


/**
 * Created by zhoubo on 15-9-6.
 */

public interface IEventListener<T> extends EventListener
{
	public void handle(IEvent<T> event);
}
