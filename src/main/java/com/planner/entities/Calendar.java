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

@Entity
@Table( name = "CALENDAR" )
public class Calendar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864357124242646337L;
	
	public Calendar(){}

	public Calendar(Date workingDate, String description, Long userId) {
		this.workingDate = workingDate;
		this.description = description;
		this.userId = userId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "CALENDAR_ID")
	private Long calendarId;
	@Column(name = "WORKING_DATE")
	private Date workingDate;
	private String description;
	@Column(name = "USER_ID")
	private Long userId;
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

}
