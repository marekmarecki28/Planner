package com.planner.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import com.planner.dao.CalendarDAO;
import com.planner.dao.EmployeeDAO;
import com.planner.encoders.EmployeeEncoder;
import com.planner.entities.Calendar;
import com.planner.entities.Employee;

public class Plan {
	
	private Long employeeId;
	
	@Property
	private String pn;
	@Property
	private String wt;
	@Property
	private String sr;
	@Property
	private String czw;
	@Property
	private String pt;
	@Property
	private String sb;
	@Property
	private String nd;
	
	@Property
	private Employee employee;
	
	@Property
	private Calendar calendar;
	
	@Property
    private SelectModel personsModel;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Inject
	private CalendarDAO calendarDAO;
	
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
		System.out.println("Poniedzialek: " + pn);
        return employeeId;
    }

    void onActivate(EventContext context) {
        if (context.getCount() > 0) {
            employeeId = context.get(Long.class, 0);
        }
    }
	
	void onPrepareForRender() {
		List<Employee> employees = getEmployees();
		
		if (employeeId != null) {
            employee = findPersonInList(employeeId, employees);
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

	public boolean isSubmitted() {
		if(employee != null)
			return true;
		else
			return false;
	}
	
	public List<Calendar> getCalendars()
	{
		return calendarDAO.getCalendars();
	}
}
