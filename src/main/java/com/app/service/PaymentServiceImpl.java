package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.exception.BookingException;
import com.app.exception.PaymentException;
import com.app.exception.SeatLockException;
import com.app.model.Booking;
import com.app.model.User;
import com.app.repository.BookingRepo;
import com.app.repository.SeatLockRepo;
import com.app.repository.UserRepo;

public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private BookingService bookingService;
	
	
	@Override
	public boolean paymentSucessfull() throws PaymentException {
		
		return true;
	}

	@Override
	public boolean paymentUncessfull(Integer bookingId, Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
