package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.enums.BookingStatus;
import com.app.model.Booking;
import com.app.model.Shows;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	//@Query("select p from Product p where p.category.categoryName = ?1")
	
	@Query("from Booking where shows = ?1") 
	public List<Booking> getBookedSeats(Shows shows) ;
	
	@Query("from Booking where userId = ?1 AND shows = ?2 AND bookingStatus = ?3")
	public List<Booking> getBookingByUserShowAndStatus(Integer userId,Shows shows,BookingStatus bookingStatus) ;
	
}
