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

import com.planner.dao.HotelDAO;
import com.planner.dao.PositionsDAO;
import com.planner.encoders.PositionEncoder;
import com.planner.entities.Hotel;
import com.planner.entities.Position;
import com.planner.enums.Sex;

public class CreatePosition {
	
	@InjectComponent
    private BeanEditForm createPositionForm;
	
	@Property
	private Position position;
	
	@Property
	@Persist
	private Hotel hotel;
	
	@Inject
	private PositionsDAO positionsDAO;
	
	@Inject
	private HotelDAO hotelDAO;
	

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
	
	void onPrepareFromCreatePositionForm() throws Exception {
        position = new Position();
    }
	
	 void onValidateFromCreatepositionForm() 
	 {
        if (createPositionForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	position.setHotelId(hotel.getHotelId());
            positionsDAO.createPosition(position);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
        	createPositionForm.recordError(e.getMessage());
        }
     }
	 
	 Object onSuccess()
    {
    	return Positions.class;
    }

}
