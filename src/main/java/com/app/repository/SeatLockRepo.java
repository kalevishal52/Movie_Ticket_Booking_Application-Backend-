package com.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Seat;
import com.app.model.SeatLock;
import com.app.model.Shows;

public interface SeatLockRepo extends JpaRepository<SeatLock, Integer> {


	@Query("select s.seatId from SeatLock s where s.showsId = ?1")
	public List<Integer> getLockedSeats(Integer showsId) ;
	
	public List<SeatLock> findByShowsId(Integer seatId) ;
	
	public List<SeatLock> findByLockedByUser(Integer userId) ;
}













