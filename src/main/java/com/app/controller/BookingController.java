package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BookingException;
import com.app.exception.SeatLockException;
import com.app.exception.SeatPermantlyUnavailableException;
import com.app.exception.SeatTemporaryUnavailableException;
import com.app.model.dto.BookingDTO;
import com.app.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/bookings/")
	public ResponseEntity<BookingDTO> bookMovieHandler(@Valid @RequestParam("userId") Integer userId, 
														@RequestParam("showId") Integer showId,
														@RequestBody List<Integer> listOfSeatId) 
														throws BookingException, 
														SeatLockException, 
														SeatPermantlyUnavailableException, 
														SeatTemporaryUnavailableException {
		
		BookingDTO bookingDTO =  bookingService.bookAMovie(userId, showId, listOfSeatId) ;
		
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.CREATED) ;
		
	}
	
	@PostMapping("/bookings/conform")
	public ResponseEntity<BookingDTO> conformBookingHandler(@RequestParam("bookingId") Integer bookingId,
															@RequestParam("userId") Integer userId) throws BookingException, SeatLockException {
		
		BookingDTO bookingDTO = bookingService.conformBooking(bookingId, userId);
		
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.ACCEPTED) ;
		
	}
	
	@GetMapping("/bookings/")
	public ResponseEntity<BookingDTO> getBookingDetailsByIdHankler(@RequestParam("bookingId") Integer bookingId) throws BookingException {
		
		BookingDTO bookingDTO =  bookingService.getBookingDetails(bookingId);
		
		return new ResponseEntity<BookingDTO>(bookingDTO,HttpStatus.OK);
		
	}
	
	
	
	
	
	
}











