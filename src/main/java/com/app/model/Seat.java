package com.app.model;

import java.util.List;

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
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seatId;
	private Integer rowId;
	private Integer colId;
	public Seat(Integer rowId, Integer colId) {
		super();
		this.rowId = rowId;
		this.colId = colId;
	}
	
	
	
}
