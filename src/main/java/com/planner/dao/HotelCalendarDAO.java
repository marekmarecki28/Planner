package com.planner.dao;

import java.util.List;

import com.planner.entities.HotelCalendar;

public interface HotelCalendarDAO {

	List<HotelCalendar> getHotelCalendars(Integer week, Integer year, Long hotelId);

}
