package com.app.service;

import com.app.exception.ScreenException;
import com.app.model.Screen;

public interface ScreenService {

	public Screen addScreenToTheater(Integer theaterId,String screenName) throws ScreenException ;
	
	public Screen addSeatsToScreen(Integer rows,Integer cols,Integer screenId,Double seatPrice) throws ScreenException ;
}
