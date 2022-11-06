package com.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer movieId;
	
	private String name;
	private String genre;
	private String duration;
	private boolean isIn2D;
	private boolean isIn3d;
	private LocalDate releaseDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cast> casts = new ArrayList<>() ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Rating ratingInfo;
	
	private String posterURL;
	
}
