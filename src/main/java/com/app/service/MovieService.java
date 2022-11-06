package com.app.service;

import java.util.List;

import com.app.exception.MovieException;
import com.app.model.Movie;
import com.app.model.dto.MovieDTO;
import com.app.model.dto.ShowInfo;

public interface MovieService {

	public MovieDTO addMovie(MovieDTO movieDTO) throws MovieException;
	
	public List<Movie> getMoviesByName(String movieName) throws MovieException ;
	
	public List<MovieDTO> getMoviesFromDate(String date) throws MovieException;
}
