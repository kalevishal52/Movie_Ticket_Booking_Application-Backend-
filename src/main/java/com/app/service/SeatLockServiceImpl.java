package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.enums.BookingStatus;
import com.app.exception.SeatLockException;
import com.app.model.Booking;
import com.app.model.Seat;
import com.app.model.SeatLock;
import com.app.model.Shows;
import com.app.repository.BookingRepo;
import com.app.repository.SeatLockRepo;
import com.app.repository.SeatRepo;

@Service
public class SeatLockServiceImpl implements SeatLockService {
	
	final private Integer lockTimeInSeconds = 300 ;
	
	@Autowired
	private SeatLockRepo seatLockRepo;
	
	@Autowired
	private SeatRepo seatRepo;
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Override
	public List<Seat> getAllLockedSeats(Shows show) throws SeatLockException {
		
		this.validateLock(show);

		List<Integer> lockedSeatIds = seatLockRepo.getLockedSeats(show.getShowId());
	 	
	 	List<Seat> lockedSeats = new ArrayList<>();
	 	for(Integer seatId : lockedSeatIds) {
	 		lockedSeats.add(seatRepo.findById(seatId).get() );
	 	}
		
	 	return lockedSeats;
	}

	@Override
	public boolean lockSeats(Shows shows, List<Seat> seats, Integer userId) throws SeatLockException {
		
		List<Seat> lockedSeats = this.getAllLockedSeats(shows);
		
		for(Seat singleSeat : seats) {
			if(lockedSeats.contains(singleSeat)) 
				throw new SeatLockException("The seat you are tryiing to Book is Temprorly Locked! You can choose Another seats") ;
		}	
		
		for(Seat singleSeat : seats) {
			System.out.println("Inside_+++");
			seatLockRepo.save(new SeatLock(singleSeat.getSeatId(), shows.getShowId(), lockTimeInSeconds, LocalDateTime.now(), userId));
		}
		
		
		return true;
	}


	@Override
	public boolean validateLock(Shows shows) throws SeatLockException {
		
		List<SeatLock> seatLocks = seatLockRepo.findByShowsId(shows.getShowId()) ;
		
		boolean flag = true;
		
		for(SeatLock seatLock : seatLocks) {
			
			LocalDateTime dateTimeNow = LocalDateTime.now(); 
			LocalDateTime expiryTime = seatLock.getDateTime().plusSeconds(lockTimeInSeconds) ;
			
//			System.out.println(dateTimeNow);
//			System.out.println(expiryTime);
//			System.out.println("__________");
			
			if(dateTimeNow.isAfter(expiryTime)) {

				seatLockRepo.delete(seatLock);
				
				List<Booking> expiredBookings =  bookingRepo.getBookingByUserShowAndStatus(seatLock.getLockedByUser(), shows, BookingStatus.Created) ;
				
				for(Booking expireBooking : expiredBookings) {
					expireBooking.setBookingStatus(BookingStatus.Expired) ;
					bookingRepo.save(expireBooking);
				}
				
//				Booking currentBooking = bookingRepo.findById(seatLock.getBookingId()).orElseThrow(()->new SeatLockException("Booking not found")) ;
//				currentBooking.setBookingStatus(BookingStatus.Expired);
//				bookingRepo.save(currentBooking);
				
				flag = false;
			}
		}
		
		
		return flag;
	}

}

















