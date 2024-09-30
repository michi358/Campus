package model;

import java.sql.Timestamp;

public class Todo {
	private int id;
	private String task;
	private Timestamp createdAt;
	private int staffId;
	
	public Todo(int id, String task, Timestamp createdAt,int staffId) {
		this.id = id;
		this.task = task;
		this.createdAt =createdAt;
		this.staffId = staffId;
	}
	
	public int getId(){
		return id;
	}
	public String getTask() {
		return task;
	}
	public  Timestamp getCreatedAt(){
		return createdAt;
	}
	public int getStaffId() {
		return staffId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
}
