package com.app.service;

import java.util.List;

import com.app.exception.BookingException;
import com.app.exception.MovieException;
import com.app.exception.SeatLockException;
import com.app.exception.ShowExcepion;
import com.app.model.Seat;
import com.app.model.dto.ShowInfo;
import com.app.model.dto.ShowsDTO;

public interface ShowsService {

	public ShowsDTO addShowsToScreen(ShowsDTO showsDTO) throws ShowExcepion;
	
	public List<Seat> getAvailableSeats(Integer showId) throws ShowExcepion, BookingException, SeatLockException;
	
	public List<ShowInfo> getShowsByMovieId(Integer movieId) throws ShowExcepion ;
}
