package com.planner.pages;


import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.planner.dao.HotelDAO;
import com.planner.entities.Hotel;

/**
 * Start page of application planner.
 */
public class Index
{
  @Inject
  private Logger logger;

  @InjectPage
  private About about;
  
  @InjectPage
  private Plan plan;
  
  @InjectPage
  private Positions	positions;
  
  @Inject
  private Session session;
  
  @Inject
  private HotelDAO hotelDAO;
  
  @Property
  @Persist
  private Hotel hotel;
  
  public List<Hotel> getHotels()
  {
	  return hotelDAO.getHotels();
  }
 
}
