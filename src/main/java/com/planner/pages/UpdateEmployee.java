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
import com.planner.dao.PositionsDAO;
import com.planner.encoders.PositionEncoder;
import com.planner.entities.Employee;
import com.planner.entities.Position;
import com.planner.enums.Sex;

public class UpdateEmployee {
	
	@Property
	@Persist
	private Employee employee;
	
	@Property
	private Position position;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Inject
	private PositionsDAO positionsDAO;
	
	@Property
    private SelectModel positionsModel;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	@Property
	Sex sex;
	
	@InjectComponent
    private BeanEditForm updateEmployeeForm;
	
	void onActivate(EventContext context)
	{
		if(context.getCount() > 0)
    	{
    		if(employee == null)
    			employee = employeeDAO.getEmployee(context.get(Long.class,0));
    		
    		if(employee != null && employee.getEmployeeId() != context.get(Long.class,0))
    		{
    			employee = employeeDAO.getEmployee(context.get(Long.class,0));
    		}

    	}
	}
	
	public PositionEncoder getPositionEncoder()
	{
		return new PositionEncoder(positionsDAO);
	}
	
	void setupRender() {
        employee = employeeDAO.getEmployee(employee.getEmployeeId());
        position = positionsDAO.getPosition(employee.getPositionId());
    }
	
	Object onSuccess() {
	    return Index.class;
    }
	
	void onPrepareForRender() {
	 	List<Position> positions = positionsDAO.getPositions();
        positionsModel = selectModelFactory.create(positions, "description");
    }
	
	 void onValidateFromUpdateEmployeeForm() {

        if (updateEmployeeForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	employee.setPositionId(position.getPositionId());
            employeeDAO.updateEmployee(employee);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	updateEmployeeForm.recordError(e.getMessage());
        }
    }

}
