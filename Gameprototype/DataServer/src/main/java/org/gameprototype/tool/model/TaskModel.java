package org.gameprototype.tool.model;

public class TaskModel {
	// 任务
	private Runnable execut;
	// 执行时间
	private long runTime;
	//任务ID
	private long id;

	public TaskModel(long id, Runnable execut, long runTime) {
		this.id = id;
		this.execut = execut;
		this.runTime = runTime;
	}
	
	public void run(){
		execut.run();
	}

	public Runnable getExecut() {
		return execut;
	}

	public void setExecut(Runnable execut) {
		this.execut = execut;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

}
