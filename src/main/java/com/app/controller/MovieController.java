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

import com.app.exception.MovieException;
import com.app.model.Movie;
import com.app.model.dto.MovieDTO;
import com.app.model.dto.ShowInfo;
import com.app.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/movies")
	public ResponseEntity<MovieDTO> addMovieHandler(@RequestBody MovieDTO movieDTO) throws MovieException {
		
		MovieDTO registeredMovie =  movieService.addMovie(movieDTO) ;
		
		return new ResponseEntity<MovieDTO>(registeredMovie,HttpStatus.CREATED) ;
	}
	
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getMoviesByNameHandler(@RequestParam("movieName") String movieName) throws MovieException {
		
		List<Movie> movies = movieService.getMoviesByName(movieName);
		
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK) ;
	}
	
	@GetMapping("/movies/dates")
	public ResponseEntity<List<MovieDTO>> getMovieFromDateHandler(@RequestParam("dateFrom") String dateFrom) throws MovieException {
		
		List<MovieDTO> moviesList = movieService.getMoviesFromDate(dateFrom);
		
		return new ResponseEntity<List<MovieDTO>>(moviesList,HttpStatus.OK) ;
		
	}
	
}

