package com.app.service;

import java.util.List;

import com.app.exception.BookingException;
import com.app.exception.SeatLockException;
import com.app.exception.SeatPermantlyUnavailableException;
import com.app.exception.SeatTemporaryUnavailableException;
import com.app.model.Seat;
import com.app.model.Shows;
import com.app.model.dto.BookingDTO;

public interface BookingService {

	public BookingDTO bookAMovie(Integer userId,Integer showId,List<Integer> listOfSeatId) throws BookingException, SeatLockException, SeatPermantlyUnavailableException, SeatTemporaryUnavailableException;
	
	public List<Seat> getBookedSeats(Shows shows) throws BookingException ;
	
	public BookingDTO conformBooking(Integer bookingId,Integer userId) throws BookingException, SeatLockException;
	
	public BookingDTO getBookingDetails(Integer bookingId) throws BookingException;
	
}
