package com.planner.entities;

import java.util.Date;
import java.util.List;

public class EmployeeCalendar {
	
	public EmployeeCalendar(){}
	
	public EmployeeCalendar(Date workDate, String day, Long calendarId, Long employeeId,
			List<UserCalendarDescr> listUserCalendarDescr) {
		this.workDate = workDate;
		this.day = day;
		this.calendarId = calendarId;
		this.employeeId = employeeId;
		this.listUserCalendarDescr = listUserCalendarDescr;
	}

	private Date workDate;
	private String day;
	private Long calendarId;
	private Long employeeId;
	private List<UserCalendarDescr> listUserCalendarDescr;
	
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
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
	public List<UserCalendarDescr> getListUserCalendarDescr() {
		return listUserCalendarDescr;
	}
	public void setListUserCalendarDescr(List<UserCalendarDescr> listUserCalendarDescr) {
		this.listUserCalendarDescr = listUserCalendarDescr;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

}
