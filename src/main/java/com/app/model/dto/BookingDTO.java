package com.app.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.enums.BookingStatus;
import com.app.model.Shows;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {


	private Integer bookingId;
	private Integer userId;

	private Shows shows;
	
	private List<Integer> seatsId = new ArrayList<>();
	
	private BookingStatus bookingStatus; 
	
}








