package com.planner.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table( name = "USERCALENDAR" )
public class UserCalendar {
	
	public UserCalendar(){}
	
	public UserCalendar(Long calendarId, String description, Long userId, String workStart, String workEnd) {
		this.calendarId = calendarId;
		this.description = description;
		this.userId = userId;
		this.workStart = workStart;
		this.workEnd = workEnd;
	}
	
	@Id
	@NonVisual
	@Column(name = "CALENDAR_ID")
	private Long calendarId;
	private String description;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "WORK_START")
	private String workStart;
	@Column(name = "WORK_END")
	private String workEnd;
	public Long getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	
}
