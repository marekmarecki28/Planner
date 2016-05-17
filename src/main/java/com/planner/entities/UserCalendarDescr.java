package com.planner.entities;

public class UserCalendarDescr {
	
	public UserCalendarDescr(){}
	
	
	public UserCalendarDescr(Long userCalendarId, Long calendarId, Long employeeId, String fullName, String description,
			String workStart, String workEnd) {
		this.userCalendarId = userCalendarId;
		this.calendarId = calendarId;
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.description = description;
		this.workStart = workStart;
		this.workEnd = workEnd;
	}

	private Long userCalendarId;
	private Long calendarId;
	private Long employeeId;
	private String fullName;
	private String description;
	private String workStart;
	private String workEnd;
	
	public Long getUserCalendarId() {
		return userCalendarId;
	}
	public void setUserCalendarId(Long userCalendarId) {
		this.userCalendarId = userCalendarId;
	}
	public Long getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkStart() {
		return workStart;
	}
	public void setWorkStart(String workStart) {
		this.workStart = workStart;
	}
	public String getWorkEnd() {
		return workEnd;
	}
	public void setWorkEnd(String workEnd) {
		this.workEnd = workEnd;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
