package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Theatre;

public interface TheatreRepo  extends JpaRepository<Theatre, Integer>  {

	@Query("from Theatre where address.city = ?1 OR address.pincode = ?1") 
	public List<Theatre> findTheatreByAddress(String query) ;
	
	public List<Theatre> findByName(String name);
}
