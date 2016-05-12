package com.planner.entities;

import java.util.Date;
import java.util.List;

public class HotelCalendar {
	
	public HotelCalendar(){}
	
	public HotelCalendar(Date workDate, List<UserCalendar> listUserCalendar) {
		this.workDate = workDate;
		this.listUserCalendar = listUserCalendar;
	}
	private Date workDate;
	private List<UserCalendar> listUserCalendar;
	
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public List<UserCalendar> getListUserCalendar() {
		return listUserCalendar;
	}
	public void setListUserCalendar(List<UserCalendar> listUserCalendar) {
		this.listUserCalendar = listUserCalendar;
	}
}
