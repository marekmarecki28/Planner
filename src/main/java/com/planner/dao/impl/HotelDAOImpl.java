package com.planner.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.planner.dao.HotelDAO;
import com.planner.entities.Employee;
import com.planner.entities.Hotel;
import com.planner.entities.UserCalendar;

public class HotelDAOImpl implements HotelDAO{
	
	private final Session session;

	public HotelDAOImpl(Session s) {
		session = s;
	}
	
	public List<Hotel> getHotels()
	{
		List<Hotel> hotels = session.createCriteria(Hotel.class).list();
		return hotels;
	}

	@Override
	public List<String> getHotelNames() {
		List<String> hotelNames = session.createCriteria(Hotel.class).setProjection(Projections.property("hotelName")).list();
		return hotelNames;
	}

	@Override
	public Hotel getHotelById(Long hotelId) {
		Hotel hotel = (Hotel) session.createQuery("from Hotel where hotel_id = " + hotelId).uniqueResult();
		return hotel;
	}

	@Override
	public void createHotel(Hotel hotel) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.save(hotel); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

	@Override
	public void deleteHotel(Long id) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 Hotel hotel = (Hotel) session.load(Hotel.class,id);
			 session.delete(hotel);
			 
			 List<Employee> employees = session.createQuery("from Employee where hotel_id = " + id).list();
			 
			 for (Employee employee : employees)
			 {
				 session.load(Employee.class, employee.getEmployeeId());
				 session.delete(employee);
				 
				 List<UserCalendar> userCalendars = session.createQuery("from UserCalendar where user_id = " + employee.getEmployeeId()).list();
				 
				 for (UserCalendar userCalendar : userCalendars)
				 {
					 session.load(UserCalendar.class, userCalendar.getUserCalendarId());
					 session.delete(userCalendar);
				 }
			 }
			 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
}
