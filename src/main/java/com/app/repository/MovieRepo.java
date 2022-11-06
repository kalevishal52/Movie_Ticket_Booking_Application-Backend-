package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
	
	public List<Movie> findByName(String name) ;
	
//	@Query("from Movie where name LIKE '%?1%'") 
//	public List<Movie> getMoviesByName(String movieName) ;
	
	@Query("from Movie where releaseDate >= ?1 order by ratingInfo.rating")
	public List<Movie> getMoviesFromDate(LocalDate date) ;
	
}
