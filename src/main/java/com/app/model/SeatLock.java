package com.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatLock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seatLockId;
	
	private Integer seatId;
	
	private Integer showsId;
	private Integer timeoutInSeconds;
	private LocalDateTime dateTime;
	private Integer lockedByUser;
	
	public SeatLock(Integer seatId, Integer showsId, Integer timeoutInSeconds, LocalDateTime dateTime,
			Integer lockedByUser) {
		super();
		this.seatId = seatId;
		this.showsId = showsId;
		this.timeoutInSeconds = timeoutInSeconds;
		this.dateTime = dateTime;
		this.lockedByUser = lockedByUser;
	}


	
	
	
	
	
	
}
