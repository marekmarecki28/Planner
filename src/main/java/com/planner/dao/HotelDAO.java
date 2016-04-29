package com.planner.dao;

import java.util.List;

import com.planner.entities.Hotel;

public interface HotelDAO {
	
	public List<Hotel> getHotels();
	public List<String> getHotelNames();
}
