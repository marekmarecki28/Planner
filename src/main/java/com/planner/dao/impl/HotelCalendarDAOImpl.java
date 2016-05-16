package com.planner.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.planner.dao.CalendarDAO;
import com.planner.dao.HotelCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;
import com.planner.entities.HotelCalendar;
import com.planner.entities.UserCalendar;
import com.planner.entities.UserCalendarDescr;

public class HotelCalendarDAOImpl implements HotelCalendarDAO {

	private final Session session;
	
	@Inject
	private CalendarDAO calendarDAO;

	public HotelCalendarDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public List<HotelCalendar> getHotelCalendars(Integer week, Integer year, Long hotelId) {
		List<HotelCalendar> listHotelCalendar = new ArrayList<HotelCalendar>();
		List<Calendar> listCalendar = calendarDAO.getCalendarsWeek(week, year);
		for (Calendar cal : listCalendar)
		{
			HotelCalendar hotelCalendar = new HotelCalendar();
			hotelCalendar.setWorkDate(cal.getWorkingDate());
			hotelCalendar.setCalendarId(cal.getCalendarId());
			List<UserCalendar> listUserCalendar = session.createSQLQuery("select uc.usercalendar_id,uc.calendar_id,uc.user_id,uc.work_start,uc.work_end,uc.description from Usercalendar uc, Employee e where uc.user_id = e.employee_id and uc.calendar_id = " + cal.getCalendarId() + " and e.hotel_id = " + hotelId).addEntity(UserCalendar.class).list();
			
			List<UserCalendarDescr> listCalendarDescr = new ArrayList<UserCalendarDescr>();
			
			for (UserCalendar userCal : listUserCalendar)
			{
				Employee employee = (Employee) session.createQuery("from Employee where employee_id = " + userCal.getUserId()).uniqueResult();
				
				UserCalendarDescr userCalendarDescr = new UserCalendarDescr();
				userCalendarDescr.setCalendarId(cal.getCalendarId());
				userCalendarDescr.setDescription(userCal.getDescription());
				userCalendarDescr.setEmployeeId(userCal.getUserId());
				userCalendarDescr.setWorkStart(userCal.getWorkStart());
				userCalendarDescr.setWorkEnd(userCal.getWorkEnd());
				userCalendarDescr.setFullName(employee.getFullName());
				
				listCalendarDescr.add(userCalendarDescr);
			}
			hotelCalendar.setListUserCalendarDescr(listCalendarDescr);
			listHotelCalendar.add(hotelCalendar);
		}
		
		return listHotelCalendar;
	}

}
