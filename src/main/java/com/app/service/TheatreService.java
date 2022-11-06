package com.app.service;


import java.util.List;

import com.app.exception.TheatreException;
import com.app.model.Theatre;
import com.app.model.dto.TheatreDTO;

public interface TheatreService {

	public Theatre registerTheater(Theatre theatre) throws TheatreException;
	
	public List<TheatreDTO> findTheaterByCityORPincode(String query) throws TheatreException;
	
	public List<TheatreDTO> findTheatreByName(String theatreName) throws TheatreException;
}
