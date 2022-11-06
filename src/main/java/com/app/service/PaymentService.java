package com.app.service;

import com.app.exception.BookingException;
import com.app.exception.PaymentException;
import com.app.exception.SeatLockException;

public interface PaymentService {

	public boolean paymentSucessfull() throws PaymentException;
	
	public boolean paymentUncessfull(Integer bookingId,Integer userId) ;
	
}
