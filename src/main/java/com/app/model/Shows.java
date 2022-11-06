package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shows {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer showId;
	
	private LocalDateTime showTiming;
	private Integer durationInSeconds;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Screen screen; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;
	
	private Integer totalSeats;
	private Integer availableSeats;

}



















