package com.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Integer addressId;
	
	@NotNull(message = "streetName cannot be Null")
	@NotBlank(message = "streetName cannot be blank")
	@NotEmpty(message = "streetName cannot be empty")
	private String streetName;
	
	@NotNull(message = "city cannot be Null")
	@NotBlank(message = "city cannot be blank")
	@NotEmpty(message = "city cannot be empty")
	private String city;
	
	@NotNull(message = "pincode cannot be Null")
	@NotBlank(message = "pincode cannot be blank")
	@NotEmpty(message = "pincode cannot be empty")
	@Size(min = 6,max = 6,message = "pincode must be of size 6")
	private String pincode;
	
	
	
	
}




