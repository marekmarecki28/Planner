package com.planner.entities;

import java.util.Date;
import java.util.List;

public class HotelCalendar {
	
	public HotelCalendar(){}
	
	public HotelCalendar(Date workDate, List<UserCalendarDescr> listUserCalendarDescr) {
		this.workDate = workDate;
		this.listUserCalendarDescr = listUserCalendarDescr;
	}
	private Date workDate;
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
	
}
