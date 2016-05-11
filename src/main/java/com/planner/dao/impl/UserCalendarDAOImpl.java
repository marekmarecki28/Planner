package com.planner.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.UserCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.UserCalendar;

public class UserCalendarDAOImpl implements UserCalendarDAO {

	private final Session session;

	public UserCalendarDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public UserCalendar findUserCalendar(Long calendarId) {
		UserCalendar calendar = (UserCalendar) session.createQuery("from UserCalendar where calendar_id = " + calendarId).uniqueResult();
		return calendar;
	}

	@Override
	public void updateUserCalendar(UserCalendar userCalendar) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.update(userCalendar); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

}
