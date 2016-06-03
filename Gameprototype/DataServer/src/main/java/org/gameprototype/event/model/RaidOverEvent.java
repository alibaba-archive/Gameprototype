package org.gameprototype.event.model;

import org.springframework.context.ApplicationEvent;

public class RaidOverEvent extends ApplicationEvent {
	private int raidArea;
	public RaidOverEvent(Object source) {
		super(source);
	}
	public RaidOverEvent(Object source,int area) {
		super(source);
		raidArea=area;
	}
	

	public int getRaidArea() {
		return raidArea;
	}
	public void setRaidArea(int raidArea) {
		this.raidArea = raidArea;
	}


	private static final long serialVersionUID = 1L;

}
