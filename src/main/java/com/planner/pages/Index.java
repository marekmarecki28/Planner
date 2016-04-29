package com.planner.pages;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.planner.dao.HotelDAO;
import com.planner.entities.Hotel;

import java.util.Date;
import java.util.List;

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
  
  @Inject
  private Session session;
  
  @Inject
  private HotelDAO hotelDAO;
  
  @Property
  private Hotel hotel;
  
  public List<Hotel> getHotels()
  {
	  return hotelDAO.getHotels();
  }
 
}
