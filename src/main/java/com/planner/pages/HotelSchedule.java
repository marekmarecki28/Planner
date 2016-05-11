package com.planner.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.CalendarDAO;
import com.planner.entities.Calendar;

public class HotelSchedule {

	@Persist
	private Integer week;
	
	@Persist
	private Integer year;
	
	@Property
	private Calendar calendar;
	
	@Inject
	private CalendarDAO calendarDAO;
	
	void onSelectedFromUp()
	{
		this.week = this.week - 1;
	}
	
	void onSelectedFromDown()
	{
		this.week = this.week + 1;
	}
	
	void onActivate()
	{
		if (this.year == null || this.week == null)
		{
			setWeek();
			setYear();
		}
	}
	
	public List<Calendar> getCalendarsWeek()
	{
		//return calendarDAO.getCalendarsWeek(this.week, this.year);
		return null;
	}
	
	public void setWeek()
	{
		System.out.println("SET WEEk");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=  df.format(new Date());
		Integer week = calendarDAO.getWeekOfDate(currentDate);
		this.week = week;
	}
	
	public void setYear()
	{
		System.out.println("SET YEAR");
		this.year = 1900 + new Date().getYear();
	}

	public Integer getWeek() {
		return this.week;
	}

	public Integer getYear() {
		return this.year;
	}

}
