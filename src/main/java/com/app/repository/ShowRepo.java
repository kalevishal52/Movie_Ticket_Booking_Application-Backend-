package com.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Movie;
import com.app.model.Shows;

public interface ShowRepo extends JpaRepository<Shows, Integer>  {

	@Query("from Shows where movie = ?1 AND showTiming > ?2")
	public List<Shows> findCurrentShowsByMovie(Movie movie,LocalDateTime dateTime) ;
	
}
