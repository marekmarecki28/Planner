package com.planner.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.CalendarDAO;
import com.planner.dao.EmployeeDAO;
import com.planner.dao.UserCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;
import com.planner.entities.UserCalendar;

public class CalendarUpdate {
	
	@Property
	private Long employeeId;
	
	@Property
	private Long calendarId;
	
	@Property
	private Long hotelId;
	
	@Property
	private Long userCalendarId;
	
	@Property
	@Persist
	private Calendar calendar;
	
	@Property
	@Persist
	private Employee employee;
	
	@Property
	@Persist
	private UserCalendar userCalendar;
	
	@Property
	private String selStart;
	
	@Property
	private String selEnd;
	
	@Inject
	private UserCalendarDAO userCalendarDAO;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Inject
	private CalendarDAO calendarDAO;

	@InjectPage
	private Plan plan;

    @InjectComponent
    private BeanEditForm calendarForm;
    
    @InjectComponent
    private Form createForm;
    
    
//    void onActivate(Long calendarId, Long employeeId) {
//    	//System.out.println("Cal id: " + calendarId + " empl id: " + employeeId);
//        this.calendarId = calendarId;
//        this.employeeId = employeeId;
//    }
    
    Long onPassivate()
    {
    	return this.hotelId;
    }
    
    boolean onActivate(Long hotelId, Long employeeId, Long calendarId, Long userCalendarId) {
    	System.out.println("OnActivate4");

    	if(employee == null) employee = employeeDAO.getEmployee(employeeId);
    	if(calendar == null) calendar = calendarDAO.findCalendar(calendarId);
    	
    	if(employee != null && employee.getEmployeeId() != employeeId) employee = employeeDAO.getEmployee(employeeId);
    	if(calendar != null && calendar.getCalendarId() != calendarId) calendar = calendarDAO.findCalendar(calendarId);
    	
    	this.userCalendarId = userCalendarId;
    	this.employeeId = employee.getEmployeeId();
    	this.calendarId = calendar.getCalendarId();
    	this.hotelId = hotelId;
    	return true;
    }
    
    void onActivate(Long hotelId, Long employeeId, Long calendarId) {
    	System.out.println("OnActivate3 " + hotelId + " " + employeeId + " " + calendarId);
    	
    	if(employee == null) employee = employeeDAO.getEmployee(employeeId);
    	if(calendar == null) calendar = calendarDAO.findCalendar(calendarId);
    	
    	if(employee != null && employee.getEmployeeId() != employeeId) employee = employeeDAO.getEmployee(employeeId);
    	if(calendar != null && calendar.getCalendarId() != calendarId) calendar = calendarDAO.findCalendar(calendarId);
    	
    	this.userCalendarId = null;
    	this.employeeId = employee.getEmployeeId();
    	this.calendarId = calendar.getCalendarId();
    	this.hotelId = hotelId;
    }
    
    void setupRender() {
    	System.out.println("setupRender " + userCalendar);
    	if(userCalendarId != null)
    	{
    		System.out.println("setupRender");
    	userCalendar = userCalendarDAO.findUserCalendar(calendarId,employeeId,userCalendarId);
    	}
    }
    

    void onValidateFromCalendarForm() {
    	System.out.println("onValidateFromCalendarForm");
        if (calendarForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	
            if (selStart != null) userCalendar.setWorkStart(selStart);
            if (selEnd != null)userCalendar.setWorkEnd(selEnd);
            
            userCalendarDAO.updateUserCalendar(userCalendar);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	System.out.println("ERRRORORRO!!!");
        	calendarForm.recordError(e.getMessage());
        }
        System.out.println("END onValidateFromCalendarForm!!!");
    }
    
    void onPrepareFromCreateForm() throws Exception {
        userCalendar = new UserCalendar();
    }
    
    void onValidateFromCreateForm() {
    	System.out.println("ONVALIDATE FORM CREWATES");
        if (createForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	if (selStart != null) userCalendar.setWorkStart(selStart);
            if (selEnd != null)userCalendar.setWorkEnd(selEnd);
            System.out.println("Cal id: " + calendar.getCalendarId() + " empl id: " + employee.getEmployeeId());
            userCalendar.setCalendarId(calendar.getCalendarId());
            userCalendar.setUserId(employee.getEmployeeId());
            
            userCalendarDAO.createUserCalendar(userCalendar);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	calendarForm.recordError(e.getMessage());
        }
    }
    
    void onSuccessFromCreateForm() {
    	userCalendarId = userCalendar.getUserCalendarId();
    }
      
    
    Object onSuccess()
    {
    	System.out.println("END onSuccess!!!");
    	return HotelSchedule.class;
    }
    
    public List<String> getHours() {
    	List<String> list= new ArrayList<String>();
		list.add("06:00");
		list.add("06:30");
		list.add("07:00");
		list.add("07:30");
		list.add("08:00");
		list.add("08:30");
		list.add("09:00");
		list.add("09:30");
		list.add("10:00");
		list.add("10:30");
		list.add("11:00");
		list.add("11:30");
		list.add("12:00");
		list.add("12:30");
		list.add("13:00");
		list.add("13:30");
		list.add("14:00");
		list.add("14:30");
		list.add("15:00");
		list.add("15:30");
		list.add("16:00");
		list.add("16:30");
		list.add("17:00");
		list.add("17:30");
		list.add("18:00");
		list.add("18:30");
		list.add("19:00");
		list.add("19:30");
		list.add("20:00");
		list.add("20:30");
		list.add("21:00");
		list.add("21:30");
		list.add("22:00");
		list.add("22:30");
		list.add("23:00");
		list.add("23:30");
		list.add("00:00");
		list.add("00:30");
		list.add("01:00");
		list.add("01:30");
		list.add("02:00");
		list.add("02:30");
		list.add("03:00");
		list.add("03:30");
		list.add("04:00");
		list.add("04:30");
		list.add("05:00");
		list.add("05:30");
        
        return list;
    }
    
    public List<String> getMinutes() {
    	List<String> list= new ArrayList<String>();
    	
        for(Integer i=0; i< 60; i++)
        {
        	list.add(i,i.toString());
        }
        
        return list;
    }
}
