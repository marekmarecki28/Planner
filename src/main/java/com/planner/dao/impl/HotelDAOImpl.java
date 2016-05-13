package com.planner.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.planner.dao.HotelDAO;
import com.planner.entities.Hotel;

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
}
