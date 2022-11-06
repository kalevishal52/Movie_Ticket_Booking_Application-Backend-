package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User registerNewUser(User user) throws UserException {
		
		if(userRepo.findByMobileNumber(user.getMobileNumber()) != null) 
			throw new UserException("User already registred with mobile number: "+user.getMobileNumber()) ;
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_"+user.getRole());
		
		return userRepo.save(user);
	}

}
