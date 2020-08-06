package com.myclass.dto;

import java.sql.Date;

public class TaskDto {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int user_id;
	private int job_id;
	private int status_id;
	private String userName;
	private String jobName;
	private String statusName;
	private float percent;
	
	public TaskDto() {
	}
	
	public TaskDto(int id, String name, Date startDate, Date endDate, String userName, String jobName,
			String statusName) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
		this.jobName = jobName;
		this.statusName = statusName;
	}
	
	public TaskDto(int id, String name, Date startDate, Date endDate, int user_id, int job_id, int status_id,
			String userName, String jobName, String statusName) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user_id = user_id;
		this.job_id = job_id;
		this.status_id = status_id;
		this.userName = userName;
		this.jobName = jobName;
		this.statusName = statusName;
	}
	
	public TaskDto(int status_id, String statusName, float percent) {
		super();
		this.status_id = status_id;
		this.statusName = statusName;
		this.percent = percent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	
	
	public void setPercent(float percent) {
		this.percent = percent;
	}

	public float getPercent() {
		return percent;
	}
	
	
	
}
