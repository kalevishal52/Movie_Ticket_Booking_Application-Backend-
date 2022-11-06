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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hall {

	
	private Integer hallId;
	
	private Integer noOfSeates;
	private Integer seatPrice;
	private String screenName;
	private boolean is2dAvailable;
	private boolean is3dAvailable;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "hall")
	List<Shows> shows = new ArrayList<>() ;

	
	@ManyToOne(cascade = CascadeType.ALL)
	private Theatre theater;
	
}
