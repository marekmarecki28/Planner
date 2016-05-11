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
	public List<Calendar> getCalendarsWeek(Integer week, Integer year, Long userId) {
		String query = "select * from (select c.calendar_id, c.working_date, uc.work_start, uc.work_end, uc.description,c.week,uc.user_id from calendar c left outer join usercalendar uc on c.calendar_id = uc.calendar_id and uc.user_id = " + userId + " order by c.calendar_id asc) d where d.week = " + week + " and ( d.working_date like '%" + year + "%' )";
		//List<Calendar> calendars = session.createQuery("from Calendar where week = " + week + " and working_date like '%" + year+ "%' order by calendar_id asc").list();
		List<Calendar> calendars = session.createSQLQuery(query).addEntity(Calendar.class).list();
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

	@Override
	public List<Calendar> getCalendarsWeek(Integer week, Integer year) {
		List<Calendar> calendars = session.createQuery("from Calendar where week = " + week + " and working_date like '%" + year+ "%' order by calendar_id asc").list();
		return calendars;
	}
}
