package com.planner.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.UserCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;
import com.planner.entities.UserCalendar;

public class UserCalendarDAOImpl implements UserCalendarDAO {

	private final Session session;
	private UserCalendar userCalendar;

	public UserCalendarDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public UserCalendar findUserCalendar(Long calendarId, Long employeeId, Long userCalendarId) {
		UserCalendar calendar = (UserCalendar) session.createQuery("from UserCalendar where calendar_id = " + calendarId + " and user_id = " + employeeId + " and usercalendar_id = " + userCalendarId).uniqueResult();
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

	@Override
	public void createUserCalendar(UserCalendar userCalendar) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.save(userCalendar); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

	@Override
	public List<UserCalendar> getUserWorkHoursWeekly(Integer week, Long employeeId) {
		String query = "select uc.usercalendar_id, uc.calendar_id, uc.user_id, uc.work_start, uc.work_end, uc.description from calendar c ,usercalendar uc where c.calendar_id = uc.calendar_id and c.week = " + week + " and c.working_date like '%2016%' and uc.user_id = " + employeeId + " order by calendar_id asc";
		List<UserCalendar> calendars = session.createSQLQuery(query).addEntity(UserCalendar.class).list();
		return calendars;
	}

	@Override
	public void deleteUserCalendarById(Long id) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 userCalendar = (UserCalendar) session.load(UserCalendar.class,id);
			 session.delete(userCalendar);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

	@Override
	public void deleteUserCalendarEmployee(Long calendarId, Long employeeId) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 userCalendar = (UserCalendar) session.createQuery("from UserCalendar where calendar_id = " + calendarId + " and user_id = " + employeeId).uniqueResult();
			 session.delete(userCalendar);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
	
	@Override
	public void deleteUserCalendarEmployee(Long calendarId, Long employeeId, Long userCalendarId) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 userCalendar = (UserCalendar) session.createQuery("from UserCalendar where calendar_id = " + calendarId + " and user_id = " + employeeId + " and usercalendar_id = " + userCalendarId).uniqueResult();
			 session.delete(userCalendar);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

}
