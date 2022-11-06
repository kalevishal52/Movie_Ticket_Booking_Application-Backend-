package com.app.model.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.model.Movie;
import com.app.model.Screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowsDTO {

	private Integer showId;
	
	@NotNull(message = "showDateTime cannot be Null")
	@NotBlank(message = "showDateTime cannot be blank")
	@NotEmpty(message = "showDateTime cannot be empty")
	private String showDateTime;
	@Min(value = 1,message = "Duration should be min 1")
	private Integer durationInSeconds;
	private Integer ScreenId;
	private Integer movieId;
	
	private Integer totalSeats;
	private Integer availableSeats;
}










