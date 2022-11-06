package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService ;
	
	@PostMapping("/users")
	public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user) throws UserException {
		
		User registerUser = userService.registerNewUser(user) ;
		
		return new ResponseEntity<User>(registerUser,HttpStatus.CREATED) ;
	}
	
	
}

