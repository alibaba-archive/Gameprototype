package org.gameprototype.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

@Service("eventUtils")
public class AceEventUtils implements ApplicationContextAware {
	private ApplicationContext context;

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context=applicationContext;
	}

	
	public void DispatchEvent(ApplicationEvent event){
		this.context.publishEvent(event);
	}

}
