package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer screenId;
	private String name;
	private Integer noOfSeats;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Theatre theatre;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();
	
	private Integer seatRows;
	private Integer seatCols;
	private Double seatPrice;
	
}






