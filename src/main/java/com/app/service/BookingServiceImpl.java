package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.enums.BookingStatus;
import com.app.exception.BookingException;
import com.app.exception.SeatLockException;
import com.app.exception.SeatPermantlyUnavailableException;
import com.app.exception.SeatTemporaryUnavailableException;
import com.app.model.Booking;
import com.app.model.Seat;
import com.app.model.SeatLock;
import com.app.model.Shows;
import com.app.model.User;
import com.app.model.dto.BookingDTO;
import com.app.repository.BookingRepo;
import com.app.repository.ScreenRepo;
import com.app.repository.SeatLockRepo;
import com.app.repository.SeatRepo;
import com.app.repository.ShowRepo;
import com.app.repository.UserRepo;
import com.app.repository.UserSessionRepo;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private ScreenRepo screenRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@Autowired
	private ShowRepo showRepo;
	
	@Autowired
	private SeatLockService seatLockService;
	
	@Autowired 
	private SeatRepo seatRepo;
	
	@Autowired 
	private SeatLockRepo seatLockRepo;
	
	@Override
	public BookingDTO bookAMovie(Integer userId, Integer showId,List<Integer> listOfSeatId) throws BookingException, SeatLockException, SeatPermantlyUnavailableException, SeatTemporaryUnavailableException {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new BookingException("Invalid UserID: "+userId)) ;
		
		Shows show = showRepo.findById(showId).orElseThrow(() -> new BookingException("Invalid ShowID: "+showId)) ;
		
		List<Seat> seatsToLocked = new ArrayList<>();
		
		for(Integer seatId : listOfSeatId) {
			Seat singleSeat = seatRepo.findById(seatId).orElseThrow(() -> new BookingException("INvalid Seat ID "+seatId) ) ;
			seatsToLocked.add(singleSeat);
 		}
		
		List<Seat> boookedSeats = this.getBookedSeats(show);
		
		for(Seat singleSeat : boookedSeats) {
			if(listOfSeatId.contains(singleSeat.getSeatId())) 
				throw new SeatPermantlyUnavailableException("Seat is Permanantly Booked! You can amother Seats") ;
		}
		
		boolean responce = seatLockService.lockSeats(show, seatsToLocked, userId);
		
		if(!responce)
			throw new BookingException("Seat is Not available");
		
		show.setAvailableSeats(show.getAvailableSeats() - listOfSeatId.size());	//
		showRepo.save(show) ;													//
		
		
		Booking booking = new Booking(userId, show, BookingStatus.Created) ;
		
		bookingRepo.save(booking) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingDTO);
		
		return bookingDTO;
	}

	@Override
	public List<Seat> getBookedSeats(Shows shows) throws BookingException {
		
		List<Booking> bookings = bookingRepo.getBookedSeats(shows) ;
		List<Seat> bookedSeats = new ArrayList<>();

		for(Booking singleBooking : bookings) bookedSeats.addAll(singleBooking.getSeats()) ;
		
//		List<Seat> lockedSeats = seatLockService.getAllLockedSeats(shows);
//		bookedSeats.addAll(lockedSeats);
		
		
		return bookedSeats;
	}

	@Override
	public BookingDTO conformBooking(Integer bookingId, Integer userId) throws BookingException, SeatLockException {
		
		
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingException("Invalid bookingID:"+bookingId));
		
		
		if(!booking.getUserId().equals(userId)) {
			throw new BookingException("Bad Request!");
		}
		
		User user = userRepo.findById(userId).orElseThrow(()-> new BookingException("Invalid UserID")) ;
		
		seatLockService.validateLock(booking.getShows()) ;
		
		List<SeatLock> seatLocks = seatLockRepo.findByLockedByUser(booking.getUserId()) ;
		
		if(seatLocks.size() == 0) 
			throw new BookingException("Your Time for Booking Expired!");
		
		List<Integer> seatNoList = new ArrayList<>();
		
		for(SeatLock seatLock : seatLocks) {
			
			seatNoList.add(seatLock.getSeatId()) ;
			seatLockRepo.deleteById(seatLock.getSeatLockId());
			
			Seat seat = seatRepo.findById(seatLock.getSeatId()).orElseThrow(()-> new BookingException("Invalid SeatId"));
			booking.getSeats().add(seat);
		}
		
//		Shows currentShow = booking.getShows();	/											//***
//		currentShow.setAvailableSeats(currentShow.getAvailableSeats() - seatNoList.size());	//***
//		showRepo.save(currentShow);
		
		booking.setBookingStatus(BookingStatus.Conformed);
		booking = bookingRepo.save(booking) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		
		BeanUtils.copyProperties(booking, bookingDTO);
		bookingDTO.setSeatsId(seatNoList);
		
		return bookingDTO;
	}

	@Override
	public BookingDTO getBookingDetails(Integer bookingId) throws BookingException {
		
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(()-> new BookingException("Invalid BookingID: "+bookingId)) ;
		
		BookingDTO bookingDTO = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingDTO);
		
		for(Seat seat : booking.getSeats()) {
			bookingDTO.getSeatsId().add(seat.getSeatId()) ;
		}
		
		return bookingDTO;
	}

}














