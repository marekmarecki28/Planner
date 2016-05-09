package com.planner.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.CalendarDAO;
import com.planner.entities.Calendar;

public class CalendarUpdate {
	
	@Property
	private Long calendarId;
	
	@Property
	@Persist
	private Calendar calendar;
	
	@Inject
	private CalendarDAO calendarDAO;

	@InjectPage
	private Plan plan;

    @InjectComponent
    private BeanEditForm calendarForm;
    
    Long onPassivate() {
        return calendarId;
    }
    
    void onActivate(Long calendarId) {
        this.calendarId = calendarId;
    }
    
    void setupRender() {
        calendar = calendarDAO.findCalendar(calendarId);
    }
    

    void onValidateFromCalendarForm() {

        if (calendarForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            System.out.println("------> Calendar: " + calendar.getCalendarId());
            calendarDAO.updateCalendar(calendar);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	calendarForm.recordError(e.getMessage());
        }
    }
}
