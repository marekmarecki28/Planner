package com.planner.pages;

import java.util.List;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import com.planner.dao.EmployeeDAO;
import com.planner.dao.HotelDAO;
import com.planner.dao.PositionsDAO;
import com.planner.encoders.PositionEncoder;
import com.planner.entities.Employee;
import com.planner.entities.EmployeePositions;
import com.planner.entities.Hotel;
import com.planner.entities.Position;

public class Positions {
	
	@InjectPage
	private CreateEmployee createEmployee;
	
	@InjectPage
	private CreatePosition createPosition;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Property
	private Employee employee;
	
	@Property
	@Persist
	private Hotel hotel;
	
	@Property
	@Persist
	private Position position;
	
	@Inject
	private PositionsDAO positionsDAO;
	
	@Property
    private SelectModel positionsModel;
	
	@Property
	private EmployeePositions employeePositions;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	@Inject
	private HotelDAO hotelDAO;
	
	@Property
    @Persist(PersistenceConstants.FLASH)
    private String errorMessage;
	
	@Persist
	private boolean isSubmitted;
	
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
	
	 void onPrepareForRender() {
	 	List<Position> positions = positionsDAO.getPositions(hotel.getHotelId());
        positionsModel = selectModelFactory.create(positions, "description");
    }

//	public List<Employee> getPokojowa()
//	{
//		return employeeDAO.getPokojowa(hotel.getHotelId());
//	}
//	
//	public List<Employee> getLobby()
//	{
//		return employeeDAO.getLobby(hotel.getHotelId());
//	}
//	
//	public List<Employee> getSpa()
//	{
//		return employeeDAO.getSpa(hotel.getHotelId());
//	}

    void onDelete(Long id) {

        try {
            employeeDAO.deleteEmployee(id);
        }
        catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
	
    public PositionEncoder getPositionEncoder()
	{
		return new PositionEncoder(positionsDAO);
	}
    
    void onSelectedFromSubmit()
	{
		this.isSubmitted = true;
	}
    
	public boolean isSubmitted() 
	{
		if(position != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<EmployeePositions> getEmployeePositionsList()
	{
		return employeeDAO.getEmployeePositions(position.getPositionId(), hotel.getHotelId());
	}
	
}
