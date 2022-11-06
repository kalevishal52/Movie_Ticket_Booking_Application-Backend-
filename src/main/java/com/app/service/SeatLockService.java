package com.app.service;

import java.util.List;

import com.app.exception.SeatLockException;
import com.app.model.Seat;
import com.app.model.Shows;

public interface SeatLockService {

	public List<Seat> getAllLockedSeats(Shows show) throws SeatLockException;
	
	public boolean lockSeats(Shows shows,List<Seat> seats,Integer userId) throws SeatLockException;
	
	public boolean validateLock(Shows shows) throws SeatLockException;
	
//	public boolean validateASingleLokd(Integer showId,Integer)
}
