package com.example.demo;

public class ToDo {
	private int taskId;
	private String task;
	private boolean done;

    public ToDo(int taskId, String task){
        this.taskId = taskId;
        this.task = task;
		this.done = false;
    }

	public int getTaskId() {
		return this.taskId;
	}
	public String getTask() {
		return this.task;
	}
	public boolean isDone() {
		return this.done;
	}
}