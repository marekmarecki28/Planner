package com.planner.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.HotelDAO;
import com.planner.entities.Hotel;

public class CreateHotel {
	
	@Property
	private Hotel hotel;
	
	@Inject
	private HotelDAO hotelDAO;
	
	@InjectComponent
    private BeanEditForm createHotelForm;
	
	void onPrepareFromCreateHotelForm() throws Exception {
		hotel = new Hotel();
    }
	
	void onValidateFromCreateHotelForm() 
	 {
       if (createHotelForm.getHasErrors()) {
           // We get here only if a server-side validator detected an error.
           return;
       }

       try {
           hotelDAO.createHotel(hotel);
       } catch (Exception e) {
           // Display the cause. In a real system we would try harder to get a
           // user-friendly message.
    	   createHotelForm.recordError(e.getMessage());
       }
    }
	 
	 Object onSuccess()
   {
   	return Index.class;
   }
}
