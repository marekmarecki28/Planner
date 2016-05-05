package com.planner.dao.impl;

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
	public List<Calendar> getCalendars() {
		List<Calendar> calendars = session.createCriteria(Calendar.class).list();
		return calendars;
	}

}
