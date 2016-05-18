package com.planner.entities;

import java.util.Date;
import java.util.List;

public class HotelCalendar {
	
	public HotelCalendar(){}
	
	public HotelCalendar(Date workDate, String day, Long calendarId, List<UserCalendarDescr> listUserCalendarDescr) {
		this.workDate = workDate;
		this.day = day;
		this.calendarId = calendarId;
		this.listUserCalendarDescr = listUserCalendarDescr;
	}

	private Date workDate;
	private String day;
	private Long calendarId;
	private List<UserCalendarDescr> listUserCalendarDescr;
	
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public List<UserCalendarDescr> getListUserCalendarDescr() {
		return listUserCalendarDescr;
	}

	public void setListUserCalendarDescr(List<UserCalendarDescr> listUserCalendarDescr) {
		this.listUserCalendarDescr = listUserCalendarDescr;
	}

	public Long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
