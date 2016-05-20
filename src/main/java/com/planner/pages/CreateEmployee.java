package com.planner.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import com.planner.dao.EmployeeDAO;
import com.planner.dao.HotelDAO;
import com.planner.dao.PositionsDAO;
import com.planner.encoders.PositionEncoder;
import com.planner.entities.Employee;
import com.planner.entities.Hotel;
import com.planner.entities.Position;
import com.planner.enums.Sex;

public class CreateEmployee {
	
	@InjectComponent
    private BeanEditForm createEmployeeForm;
	
	@Property
	private Employee employee;
	
	@Property
	@Persist
	private Hotel hotel;
	
	@Property
	Sex sex;
	
	@Property
	private Position position;
	
	@Inject
	private HotelDAO hotelDAO;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Inject
	private PositionsDAO positionsDAO;
	
	@Property
    private SelectModel positionsModel;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	public PositionEncoder getPositionEncoder()
	{
		return new PositionEncoder(positionsDAO);
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
	}
	
	void onPrepareFromCreateEmployeeForm() throws Exception {
        employee = new Employee();
    }
	
	 void onValidateFromCreateEmployeeForm() 
	 {
        if (createEmployeeForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	employee.setHotelId(hotel.getHotelId());
        	employee.setPositionId(position.getPositionId());
        	employee.setSex(sex);
            employeeDAO.createEmployee(employee);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	createEmployeeForm.recordError(e.getMessage());
        }
     }
	 
	 Object onSuccess()
    {
    	return Positions.class;
    }
	 
	 void onPrepareForRender() {
		 	List<Position> positions = positionsDAO.getPositions();
	        positionsModel = selectModelFactory.create(positions, "description");
	    }

}
