package com.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.app.enums.BookingStatus;
import com.app.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull(message = "Name cannot be Null")
	@NotBlank(message = "Name cannot be blank")
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@Pattern(regexp = "[7-9][0-9]{9}",message = "Mobile number should start with 7-9 and of size 10")
	private String mobileNumber;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$"
			,message = "Pass must should in size 8-20, contains atleast 1 digit,1 uppercase letter,1 special character,and no whitespace")
	private String password;
	
	@Email(message = "Enter a valid emailId")
	private String email;
	
	private String role;
	
}










