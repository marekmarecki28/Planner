package com.planner.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.planner.dao.CalendarDAO;
import com.planner.entities.Calendar;

public class CalendarDAOImpl implements CalendarDAO {
	
	private final Session session;

	public CalendarDAOImpl(Session s) {
		session = s;
	}

	@Override
	public List<Calendar> getCalendarsWeek(Integer week, Integer year) {
		List<Calendar> calendars = session.createQuery("from Calendar where week = " + week + " and working_date like '%" + year+ "%'").list();
		return calendars;
	}

	public Integer getWeekOfDate(String date)
	{
		Integer week = (Integer) session.createQuery("select week from Calendar where working_date = to_date('" + date +"','YYYY-MM-DD')").uniqueResult();
		return week;
	}
}
