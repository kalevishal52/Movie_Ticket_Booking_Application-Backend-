package com.app.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ScreenException;
import com.app.model.Screen;
import com.app.service.ScreenService;

@RestController
@RequestMapping("/theatres")
public class ScreenController {

	@Autowired
	private ScreenService screenService;
	
	@PostMapping("/screens")
	public ResponseEntity<Screen> addScreenToTheatreHandler(@Valid @RequestParam("theaterId") Integer theaterId,
				@NotNull(message = "ScreenName shouldnt be null")	@RequestParam("screenName") String screenName) throws ScreenException {
		
		Screen registerScreen  = screenService.addScreenToTheater(theaterId, screenName);
		
		return new ResponseEntity<Screen>(registerScreen,HttpStatus.CREATED) ;
	}
	
	@PostMapping("/screens/seats")
	public ResponseEntity<Screen> addSeatsToScreenHandler(
			
			@Valid @Min(value = 1,message = "row should be min 1") @RequestParam("noOfRows") Integer rows,
			@Min(value = 1,message = "col should be min 1") @RequestParam("noOfCols") Integer cols,
		    @RequestParam("screenId") Integer screenId,
		    @Min(value = 1,message = "Seat price cant be negative/zeor") @RequestParam("seatPrice") Double seatPrice) throws ScreenException {
		
		
		Screen screenWithaddedSeat  = screenService.addSeatsToScreen(rows, cols, screenId,seatPrice);
		
		return new ResponseEntity<Screen>(screenWithaddedSeat,HttpStatus.CREATED) ;
		
	}
	
}
