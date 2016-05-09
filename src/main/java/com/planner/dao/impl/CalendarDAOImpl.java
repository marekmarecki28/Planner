package com.planner.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.CalendarDAO;
import com.planner.entities.Calendar;

public class CalendarDAOImpl implements CalendarDAO {
	
	private final Session session;

	public CalendarDAOImpl(Session s) {
		session = s;
	}

	@Override
	public List<Calendar> getCalendarsWeek(Integer week, Integer year) {
		List<Calendar> calendars = session.createQuery("from Calendar where week = " + week + " and working_date like '%" + year+ "%' order by calendar_id asc").list();
		return calendars;
	}

	public Integer getWeekOfDate(String date)
	{
		Integer week = (Integer) session.createQuery("select week from Calendar where working_date = to_date('" + date +"','YYYY-MM-DD')").uniqueResult();
		return week;
	}

	@Override
	public Calendar findCalendar(Long calendarId) {
		Calendar calendar = (Calendar) session.createQuery("from Calendar where calendar_id = " + calendarId).uniqueResult();
		return calendar;
	}

	@Override
	public void updateCalendar(Calendar calendar) {
		System.out.println("*******UPDATE CALENDAR: " + calendar.getCalendarId());
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.update(calendar); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
}
