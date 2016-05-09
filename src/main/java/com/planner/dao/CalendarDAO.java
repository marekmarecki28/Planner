package com.planner.dao;

import java.util.List;

import com.planner.entities.Calendar;

public interface CalendarDAO {

	public List<Calendar> getCalendarsWeek(Integer week, Integer year);
	public Integer getWeekOfDate(String date);
	public Calendar findCalendar(Long calendarId);
	public void updateCalendar(Calendar calendar);

}
