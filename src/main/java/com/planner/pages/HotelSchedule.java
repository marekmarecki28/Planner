package com.planner.pages;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.CalendarDAO;
import com.planner.dao.HotelCalendarDAO;
import com.planner.dao.HotelDAO;
import com.planner.dao.UserCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.Hotel;
import com.planner.entities.HotelCalendar;
import com.planner.entities.UserCalendarDescr;

public class HotelSchedule {
	
	private final String format = "dd MMMM yyyy";

	@Persist
	private Integer week;
	
	@Persist
	private Integer year;
	
	@Property
	private Calendar calendar;
	
	@Property
	private UserCalendarDescr userCalendarDescr;
	
	@Property
	@Persist
	private Hotel hotel;
	
	@Property
	private HotelCalendar hotelCalendar;
	
	@Inject
	private CalendarDAO calendarDAO;
	
	@Inject
	private UserCalendarDAO userCalendarDAO;
	
	@Inject
	private HotelCalendarDAO hotelCalendarDAO;
	
	@Inject
	private HotelDAO hotelDAO;
	
	@Property
    @Persist(PersistenceConstants.FLASH)
    private String errorMessage;
	
	void onSelectedFromUp()
	{
		this.week = this.week - 1;
	}
	
	@Import(stylesheet="context:style.css")
	void afterRender() { }
	
	void onSelectedFromDown()
	{
		this.week = this.week + 1;
	}
	
	void onActivate(EventContext context)
	{
		
		if(context.getCount() > 0)
    	{
    		if(hotel == null)
        		hotel = hotelDAO.getHotelById(context.get(Long.class,0));
    		
    		if(hotel != null && hotel.getHotelId() != context.get(Long.class,0))
    		{
    			hotel = hotelDAO.getHotelById(context.get(Long.class,0));
    		}

    	}
		
		if (this.year == null || this.week == null)
		{
			setWeek();
			setYear();
		}
	}
	
	public List<HotelCalendar> getHotelCalendars(){
		return hotelCalendarDAO.getHotelCalendars(this.week, this.year,hotel.getHotelId());
	}
	
	public List<Calendar> getCalendarsWeek()
	{
		return calendarDAO.getCalendarsWeek(this.week, this.year);
	}	
	
	public void setWeek()
	{
		System.out.println("SET WEEk");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=  df.format(new Date());
		Integer week = calendarDAO.getWeekOfDate(currentDate);
		this.week = week;
	}
	
	public void setYear()
	{
		System.out.println("SET YEAR");
		this.year = 1900 + new Date().getYear();
	}

	public Integer getWeek() {
		return this.week;
	}

	public Integer getYear() {
		return this.year;
	}
	
	void onDelete(Long calendarId,Long employeeId, Long userCalendarId) {
        try {
            userCalendarDAO.deleteUserCalendarEmployee(calendarId,employeeId,userCalendarId);
        }
        catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
	
	public Format getFormat()
	{
	    return new SimpleDateFormat(format);
	}

}
