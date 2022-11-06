package com.app.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.model.Cast;
import com.app.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

	
	private Integer movieId;
	
	@NotNull(message = "movieName cannot be Null")
	@NotBlank(message = "movieName cannot be blank")
	@NotEmpty(message = "movieName cannot be empty")
	private String name;
	
	@NotNull(message = "genre cannot be Null")
	@NotBlank(message = "genre cannot be blank")
	@NotEmpty(message = "genre cannot be empty")
	private String genre;
	
	@NotNull(message = "duration cannot be Null")
	@NotBlank(message = "duration cannot be blank")
	@NotEmpty(message = "duration cannot be empty")
	private String duration;
	
	private boolean isIn2D;
	private boolean isIn3d;
	
	@NotNull(message = "releasingDate cannot be Null")
	@NotBlank(message = "releasingDate cannot be blank")
	@NotEmpty(message = "releasingDate cannot be empty")
	private String releasingDate;
	
	private List<Cast> casts = new ArrayList<>() ;
	
	private Rating ratingInfo;
	private String posterURL;
}










