package com.planner.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.CalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;
import com.planner.entities.EmployeeCalendar;
import com.planner.entities.UserCalendar;
import com.planner.entities.UserCalendarDescr;

public class CalendarDAOImpl implements CalendarDAO {
	
	private final Session session;

	public CalendarDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public List<EmployeeCalendar> getCalendarsWeek(Integer week, Integer year, Long userId) {
		Long calendarId = new Long(-1);
		String query = "select * from (select c.calendar_id, c.working_date, uc.work_start, uc.work_end, uc.description,c.week,uc.user_id from calendar c left outer join usercalendar uc on c.calendar_id = uc.calendar_id and uc.user_id = " + userId + " order by c.calendar_id asc) d where d.week = " + week + " and ( d.working_date like '%" + year + "%' )";
		List<Calendar> calendars = session.createSQLQuery(query).addEntity(Calendar.class).list();
		
		List<EmployeeCalendar> listEmployeeCalendar = new ArrayList<EmployeeCalendar>();
		
		for (Calendar cal : calendars)
		{
			if(calendarId != cal.getCalendarId())
			{
				calendarId = cal.getCalendarId();
				EmployeeCalendar employeeCalendar = new EmployeeCalendar();
				employeeCalendar.setCalendarId(cal.getCalendarId());
				employeeCalendar.setWorkDate(cal.getWorkingDate());
				
				List<UserCalendar> listUserCalendar = session.createSQLQuery("select uc.usercalendar_id,uc.calendar_id,uc.user_id,uc.work_start,uc.work_end,uc.description from Usercalendar uc, Employee e where uc.user_id = e.employee_id and uc.calendar_id = " + cal.getCalendarId() + " and e.employee_id = " + cal.getUserId()).addEntity(UserCalendar.class).list();
				
				List<UserCalendarDescr> listUserCalendarDescr = new ArrayList<UserCalendarDescr>();
				
				for(UserCalendar userCal : listUserCalendar)
				{
					Employee employee = (Employee) session.createQuery("from Employee where employee_id = " + userCal.getUserId()).uniqueResult();
					
					UserCalendarDescr userCalendarDescr = new UserCalendarDescr();
					userCalendarDescr.setUserCalendarId(userCal.getUserCalendarId());
					userCalendarDescr.setCalendarId(cal.getCalendarId());
					userCalendarDescr.setDescription(userCal.getDescription());
					userCalendarDescr.setEmployeeId(userCal.getUserId());
					userCalendarDescr.setWorkStart(userCal.getWorkStart());
					userCalendarDescr.setWorkEnd(userCal.getWorkEnd());
					userCalendarDescr.setFullName(employee.getFullName());
					
					listUserCalendarDescr.add(userCalendarDescr);
				}
			
			
				employeeCalendar.setListUserCalendarDescr(listUserCalendarDescr);
				listEmployeeCalendar.add(employeeCalendar);
			}
		}
		return listEmployeeCalendar;
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
