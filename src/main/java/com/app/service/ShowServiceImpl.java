package com.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BookingException;
import com.app.exception.MovieException;
import com.app.exception.SeatLockException;
import com.app.exception.ShowExcepion;
import com.app.model.Movie;
import com.app.model.Screen;
import com.app.model.Seat;
import com.app.model.Shows;
import com.app.model.dto.ShowInfo;
import com.app.model.dto.ShowsDTO;
import com.app.repository.MovieRepo;
import com.app.repository.ScreenRepo;
import com.app.repository.ShowRepo;

@Service
public class ShowServiceImpl implements ShowsService {

	@Autowired
	private ShowRepo showRepo;
	
	@Autowired
	private ScreenRepo screenRepo;
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private SeatLockService seatLockService;
	
	@Override
	public ShowsDTO addShowsToScreen(ShowsDTO showsDTO) throws ShowExcepion {
		
		Screen screen = screenRepo.findById(showsDTO.getScreenId()).orElseThrow(() -> new ShowExcepion("Invalid ScreenID: "+showsDTO.getShowId())) ;
		
		Movie movie = movieRepo.findById(showsDTO.getMovieId()).orElseThrow(() -> new ShowExcepion("Invalid MovidID: "+showsDTO.getMovieId())) ;
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");	//yyyy-MM-dd-HH-mm-ss
		String showDateInString = showsDTO.getShowDateTime() ;	//"31-10-2022-06-00-00" 
		LocalDateTime showDate = LocalDateTime.parse(showDateInString,formatter) ;
		
		if(showDate.toLocalDate().isBefore(movie.getReleaseDate())) 
			throw new ShowExcepion("Show Date Cannot be Before Releasing Date") ;
		
		Shows newShow = new Shows(); 
		BeanUtils.copyProperties(showsDTO, newShow);
		newShow.setShowTiming(showDate);
		newShow.setMovie(movie);
		newShow.setScreen(screen);
		newShow.setTotalSeats(screen.getNoOfSeats());
		newShow.setAvailableSeats(screen.getNoOfSeats());
		
		Shows registerShow = showRepo.save(newShow) ;
		
		showsDTO.setShowId(registerShow.getShowId());
		showsDTO.setAvailableSeats(registerShow.getAvailableSeats());
		showsDTO.setTotalSeats(registerShow.getTotalSeats());
		
		return showsDTO;
	}

	@Override
	public List<Seat> getAvailableSeats(Integer showId) throws ShowExcepion, BookingException, SeatLockException {
		
		Shows show = showRepo.findById(showId).orElseThrow(()-> new ShowExcepion("Invalid ShowID")) ;
		
		List<Seat> listOfBookedSeats = bookingService.getBookedSeats(show);
		List<Seat> lostOfLockedSeats =  seatLockService.getAllLockedSeats(show);
		
		List<Seat> allSeats = screenRepo.findById(show.getScreen().getScreenId())
										.orElseThrow(()-> new ShowExcepion("Invalid ScreenID!")).getSeats();
		
		List<Seat> availableSeats = new ArrayList<>(allSeats) ;
		
		availableSeats.removeAll(listOfBookedSeats) ;
		availableSeats.removeAll(lostOfLockedSeats);
		
		return availableSeats;
	}

	@Override
	public List<ShowInfo> getShowsByMovieId(Integer movieId) throws ShowExcepion {
		
		Movie movie =  movieRepo.findById(movieId).orElseThrow(()-> new ShowExcepion("Invalid MovieId : "+movieId)) ;
		
		List<Shows> showsList = showRepo.findCurrentShowsByMovie(movie, LocalDateTime.now()) ;
		
		List<ShowInfo> showInfoList = new ArrayList<>();
		
		for(Shows show : showsList) {
			
			ShowInfo showInfo = this.copyProperties(show);
			showInfoList.add(showInfo);
		}
		
		return showInfoList;
	}
	
	public ShowInfo copyProperties(Shows show) {
		
		ShowInfo showInfo = new ShowInfo(show.getShowId(), show.getShowTiming(),
										 show.getDurationInSeconds(), show.getScreen().getScreenId(), 
										 show.getScreen().getName(), show.getScreen().getNoOfSeats(), 
										 show.getAvailableSeats(),
										 show.getScreen().getTheatre().getTheatreId(), show.getScreen().getTheatre().getName(),
										 show.getScreen().getTheatre().getAddress(), show.getMovie()) ;
		
		
		return showInfo;
	}

	
	
}













