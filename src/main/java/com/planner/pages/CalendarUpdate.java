package com.planner.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.UserCalendarDAO;
import com.planner.entities.Calendar;
import com.planner.entities.UserCalendar;

public class CalendarUpdate {
	
	@Property
	@Persist
	private Long employeeId;
	
	@Property
	@Persist
	private Long calendarId;
	
	@Property
	private Long userCalendarId;
	
	@Property
	@Persist
	private Calendar calendar;
	
	@Property
	@Persist
	private UserCalendar userCalendar;
	
	@Property
	private String selStart;
	
	@Property
	private String selEnd;
	
	@Inject
	private UserCalendarDAO userCalendarDAO;

	@InjectPage
	private Plan plan;

    @InjectComponent
    private Form calendarForm;
    
    @InjectComponent
    private Form createForm;
    
    Long onPassivate() {
        return calendarId;
    }
    
    void onActivate(Long calendarId, Long employeeId) {
    	//System.out.println("Cal id: " + calendarId + " empl id: " + employeeId);
        this.calendarId = calendarId;
        this.employeeId = employeeId;
    }
    
    void setupRender() {
        userCalendar = userCalendarDAO.findUserCalendar(calendarId,employeeId);
    }
    

    void onValidateFromCalendarForm() {
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
        	calendarForm.recordError(e.getMessage());
        }
    }
    
    void onPrepareFromCreateForm() throws Exception {
        userCalendar = new UserCalendar();
    }
    
    void onValidateFromCreateForm() {

        if (createForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	if (selStart != null) userCalendar.setWorkStart(selStart);
            if (selEnd != null)userCalendar.setWorkEnd(selEnd);
            System.out.println("Cal id: " + this.calendarId + " empl id: " + this.employeeId);
            userCalendar.setCalendarId(this.calendarId);
            userCalendar.setUserId(this.employeeId);
            
            userCalendarDAO.createUserCalendar(userCalendar);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	calendarForm.recordError(e.getMessage());
        }
    }
    
    void onSuccessFromCreateForm() {
    	userCalendarId = userCalendar.getCalendarId();
    }
      
    
    Object onSuccess()
    {
    	return Plan.class;
    }
    
    public List<String> getHours() {
    	List<String> list= new ArrayList<String>();
    	
        for(Integer i=0; i< 24; i++)
        {
        	list.add(i,i.toString());
        }
        
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
