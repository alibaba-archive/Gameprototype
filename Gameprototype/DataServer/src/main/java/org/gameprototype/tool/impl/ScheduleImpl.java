package org.gameprototype.tool.impl;

import org.gameprototype.tool.ISchedule;
import org.gameprototype.tool.model.TaskModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


public class ScheduleImpl implements ISchedule {
	Timer time;
	//等待执行任务表
	private Map<Long, TaskModel> mission = new ConcurrentHashMap<Long, TaskModel>();
	//任务ID自增
	private AtomicLong mId = new AtomicLong(0);
	//任务执行时间验证
	private Calendar cd=Calendar.getInstance();
	//任务移除列表
	private List<Long> removeList=new CopyOnWriteArrayList<Long>();
//	public void init() {
//		running();
//	}
//	
	public ScheduleImpl(){
		time = new Timer("scheduleWork", false);
		running();
	}
	
	public ScheduleImpl(String name){
		time = new Timer(name, false);
		running();
	}

	public void running() {
		//time.scheduleAtFixedRate(task, delay, period)
		time.schedule(new TimerTask() {

			@Override
			public void run() {
				for (Long l : removeList) {
					mission.remove(l);
				}
				removeList.clear();
				cd=Calendar.getInstance();
				for (TaskModel model : mission.values()) {
					if(model.getRunTime()<=cd.getTimeInMillis()){
						model.run();
						removeList.add(model.getId());
					}
				}
				running();
			}
		}, 200);
	}
	@Override
	public long schedule(Runnable task, long delay) {
		// time.schedule(task, delay);
		long id = mId.addAndGet(1);
		cd=Calendar.getInstance();
		TaskModel model=new TaskModel(id, task, cd.getTimeInMillis()+delay);
		mission.put(id, model);
		return id;
	}

	@Override
	public void removeMission(long id) {
		removeList.add(id);

	}

	@Override
	public long schedule(Runnable task, Date time) {
		long t= time.getTime()-DateUtil.getNow();
		t=Math.abs(t);
		return schedule(task, t);
	}

	@Override
	public long timeSchedule(Runnable task, long time) {
		long t=time-DateUtil.getNow();
		t=Math.abs(t);
		return schedule(task, t);
	}

}
