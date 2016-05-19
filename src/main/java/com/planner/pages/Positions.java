package com.planner.pages;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.EmployeeDAO;
import com.planner.dao.HotelDAO;
import com.planner.entities.Employee;
import com.planner.entities.Hotel;

public class Positions {
	
	@InjectPage
	private CreateEmployee createEmployee;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Property
	private Employee employee;
	
	@Property
	@Persist
	private Hotel hotel;
	
	@Inject
	private HotelDAO hotelDAO;
	
	@Property
    @Persist(PersistenceConstants.FLASH)
    private String errorMessage;
	
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
	}	

	public List<Employee> getPokojowa()
	{
		return employeeDAO.getPokojowa(hotel.getHotelId());
	}
	
	public List<Employee> getLobby()
	{
		return employeeDAO.getLobby(hotel.getHotelId());
	}
	
	public List<Employee> getSpa()
	{
		return employeeDAO.getSpa(hotel.getHotelId());
	}

    void onDelete(Long id) {

        try {
            employeeDAO.deleteEmployee(id);
        }
        catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
	
	
}
