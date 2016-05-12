package com.planner.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import com.planner.dao.CalendarDAO;
import com.planner.dao.EmployeeDAO;
import com.planner.dao.UserCalendarDAO;
import com.planner.encoders.EmployeeEncoder;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;
import com.planner.entities.UserCalendar;

public class Plan {
	
	private int hourDiff;
	private int minuteDiff;
	
	@Property
	private Long employeeId;
	
	@Property
	@Persist
	private Employee employee;
	
	@Property
	private Calendar calendar;
	
	@Property
	private String userWorkHoursWeekly;
	
	@Property
	private Long calendarId;
	
	@Persist
	private Integer week;
	
	@Persist
	private Integer year;
	
	@Property
    private SelectModel personsModel;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Inject
	private CalendarDAO calendarDAO;
	
	@Inject
	private UserCalendarDAO userCalendarDAO;
	
	@InjectComponent("calendarForm")
    private Form form;
	
	public List<String> getEmployeeNames()
	{
		return employeeDAO.getEmployeeNames();
	}
	
	public List<Employee> getEmployees()
	{
		return employeeDAO.getEmployees();
	}
	
	public EmployeeEncoder getEmployeeEncoder()
	{
		return new EmployeeEncoder(employeeDAO);
	}
	
	Long onPassivate() {
        return employeeId;
    }

    void onActivate(EventContext context) {
        if (context.getCount() > 0) {
            employeeId = context.get(Long.class, 0);
        }
        
        employeeId = employee == null ? null : employee.getEmployeeId();
        
        if(this.week == null)
		{
        	setWeek();
		}
        if(this.year == null)
		{
        	setYear();
		}
    }
	
	void onPrepareForRender() {
		List<Employee> employees = getEmployees();
		
		if (employeeId != null) {
            employee = findPersonInList(employeeId, employees);
            setUserWorkHoursWeekly(this.week,employeeId);
        }
		
        personsModel = selectModelFactory.create(employees, "fullName");
    }
	
	void onValidateFromForm() {
        employeeId = employee == null ? null : employee.getEmployeeId();
    }
	
	private Employee findPersonInList(Long employeeId, List<Employee> employees) 
	{
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }
	
	void onSelectedFromUp()
	{
		this.week = this.week - 1;
	}
	
	void onSelectedFromDown()
	{
		this.week = this.week + 1;
	}
	
	void onSelectedFromSubmit()
	{
		setWeek();
		setYear();
	}

	public boolean isSubmitted() {
		if(employee != null)
			return true;
		else
			return false;
	}
	
	public List<Calendar> getCalendarsWeek()
	{
		return calendarDAO.getCalendarsWeek(this.week, this.year, employeeId);
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
	
	public void setUserWorkHoursWeekly(Integer week, Long employeeId)
    {
		StringBuffer sb = new StringBuffer();
		List<UserCalendar> listCalendars = userCalendarDAO.getUserWorkHoursWeekly(week,employeeId);
		this.hourDiff = 0;
		this.minuteDiff = 0;
		System.out.println("START HOUR DIFF= " + this.hourDiff + " MINUTE DIFF= " + this.minuteDiff);
		for (UserCalendar cal : listCalendars)
		{
			String start = cal.getWorkStart();
			String end = cal.getWorkEnd();
			
			if(start != null && end != null)
			{
				String startHour = start.split(":")[0].substring(0,1).equals("0") ? start.split(":")[0].substring(1) : start.split(":")[0];
				String startMinutes = start.split(":")[1].substring(0,1).equals("0") ? "0": start.split(":")[1];
				
				String endHour = end.split(":")[0].substring(0,1).equals("0") ? end.split(":")[0].substring(1) : end.split(":")[0];
				String endMinutes = end.split(":")[1].substring(0,1).equals("0") ? "0" : end.split(":")[1];
				
				System.out.println("START Hour: " + startHour + " minutes: " + startMinutes);
				System.out.println("END Hour: " + endHour + " minutes: " + endMinutes);
				
				this.hourDiff += Integer.parseInt(endHour) - Integer.parseInt(startHour);
				this.minuteDiff += Integer.parseInt(endMinutes) - Integer.parseInt(startMinutes);
				
				sb.append(cal.getWorkStart() + " - " + cal.getWorkEnd() + "\n");
			}
		}
//		System.out.println(" HOUR DIFF= " + this.hourDiff + " AND MINUTES DIFF= " + this.minuteDiff);
		
		if(this.minuteDiff > 30)
		{
			for(int i=0; i<this.minuteDiff;i=i+60)
			{
				this.hourDiff++;
				this.minuteDiff = this.minuteDiff - 60;
			}
		}
		
		System.out.println("END HOUR DIFF= " + this.hourDiff + " MINUTE DIFF= " + this.minuteDiff);
		
		String out = "Pracownik przepracowa³: " + this.hourDiff + " godzin";
		String outMn = " i " + this.minuteDiff + " minut";
		
		if(this.minuteDiff > 0)
			out = out + outMn;
		
		this.userWorkHoursWeekly = out;
    }
	
}
