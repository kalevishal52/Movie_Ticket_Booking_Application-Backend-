package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BookingException;
import com.app.exception.SeatLockException;
import com.app.exception.ShowExcepion;
import com.app.model.Seat;
import com.app.model.dto.ShowInfo;
import com.app.model.dto.ShowsDTO;
import com.app.service.ShowsService;

@RestController
public class ShowsController {

	@Autowired
	private ShowsService showsService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/shows/")
	public ResponseEntity<ShowsDTO> addShowToScreenHandler(@RequestBody ShowsDTO dto) throws ShowExcepion {
		
		ShowsDTO savedShow = showsService.addShowsToScreen(dto);
		
		return new ResponseEntity<ShowsDTO>(savedShow,HttpStatus.CREATED) ;
	}
	
	@GetMapping("/shows/seats")
	public ResponseEntity<List<Seat>> getAvailableSeatsHandler(@RequestParam("showId") Integer showId) throws ShowExcepion, BookingException, SeatLockException {
		
		List<Seat> availableSeats =  showsService.getAvailableSeats(showId);
		
		return new ResponseEntity<List<Seat>>(availableSeats,HttpStatus.OK) ;
	}
	
	@GetMapping("/shows/movies")
	public ResponseEntity<List<ShowInfo>> getShowsByMovieId(@RequestParam("movieId") Integer movieId) throws ShowExcepion {
		
		List<ShowInfo> showsInfo = showsService.getShowsByMovieId(movieId);
		
		return new ResponseEntity<List<ShowInfo>>(showsInfo,HttpStatus.OK);
	}
	
}
