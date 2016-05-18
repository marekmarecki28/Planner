package com.planner.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.ioc.annotations.Inject;

@Entity
@Table( name = "CALENDAR" )
public class Calendar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864357124242646337L;
	
	@Inject
	public Calendar(){}

	
	
	public Calendar(Long calendarId, Date workingDate, String day, String description, Long userId, String workStart,
			String workEnd) {
		this.calendarId = calendarId;
		this.workingDate = workingDate;
		this.day = day;
		this.description = description;
		this.userId = userId;
		this.workStart = workStart;
		this.workEnd = workEnd;
	}



	@Id
	@NonVisual
	@Column(name = "CALENDAR_ID")
	private Long calendarId;
	@Column(name = "WORKING_DATE")
	private Date workingDate;
	private String day;
	private String description;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "WORK_START")
	private String workStart;
	@Column(name = "WORK_END")
	private String workEnd;
	
	public Date getWorkingDate() {
		return workingDate;
	}
	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
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
	public Long getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
