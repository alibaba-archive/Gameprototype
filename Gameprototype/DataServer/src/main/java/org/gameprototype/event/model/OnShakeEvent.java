package org.gameprototype.event.model;

import io.netty.channel.Channel;

import org.springframework.context.ApplicationEvent;

public class OnShakeEvent extends ApplicationEvent {
	private Channel channel;
	public OnShakeEvent(Object source) {
		super(source);
	}

	public OnShakeEvent(Object source,Channel channel){
		super(source);
		this.channel=channel;
	}
	
	
	public Channel getChannel() {
		return channel;  
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -7544859293589575169L;

}
