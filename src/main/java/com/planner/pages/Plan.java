package com.planner.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.HotelDAO;
import com.planner.entities.Hotel;

public class Plan {
	
	@Property
	private String hotelName;
	
	@Inject
	private HotelDAO hotelDAO;
	
	 public List<Hotel> getHotels()
	  {
		  return hotelDAO.getHotels();
	  }
	 
	  public List<String> getHotelNames()
	  {
		  return hotelDAO.getHotelNames();
	  }
}
