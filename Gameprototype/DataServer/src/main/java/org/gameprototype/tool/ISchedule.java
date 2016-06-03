package org.gameprototype.tool;

import java.util.Date;



public interface ISchedule {
	/**
	 * 启动定时任务
	 * @param task
	 * @param delay 多少毫秒后执行
	 * @return
	 */
	long schedule(Runnable task, long delay);
	
	/**
	 * 在指定时间执行
	 * */
	long schedule(Runnable task, Date time);
	
	/***
	 * 在指定时间执行
	 */
	long timeSchedule(Runnable task, long time);
	
	/**
	 * 从任务列表删除任务
	 * @param id
	 */
	void removeMission(long id); 
}
