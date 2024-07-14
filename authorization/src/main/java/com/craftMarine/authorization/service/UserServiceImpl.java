package com.craftMarine.authorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.craftMarine.authorization.entities.MyUserDetails;
import com.craftMarine.authorization.entities.User;
import com.craftMarine.authorization.exception.MemberNotFoundException;
import com.craftMarine.authorization.repository.UserRepository;
import com.craftMarine.authorization.request.LoginRequest;
import com.craftMarine.authorization.request.RegistrationRequest;
import com.craftMarine.authorization.response.LoginResponse;
import com.craftMarine.authorization.response.UserResponse;
import com.craftMarine.authorization.util.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String register(RegistrationRequest registrationRequest) {
		
		String encodedPassword = new BCryptPasswordEncoder().encode(registrationRequest.getPassword());
		User user = new User(null, registrationRequest.getName(), encodedPassword,
				registrationRequest.getRole());
		Optional<User> findByUserName = userRepository.findByUserName(user.getUserName());
		if(findByUserName.isPresent())
			return "Admin Already Exists";
		userRepository.save(user);
		return "User registered";
	}

	@Override
	public String login(LoginRequest loginRequest) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
			MyUserDetails user = (MyUserDetails) authentication.getPrincipal();

			
			LoginResponse loginResponse = new LoginResponse(user.getRoles(),jwtTokenUtil.generateAccessToken(user),"");
			
			return "Login success";

		} catch (BadCredentialsException ex) {
			return "Invalid cred";
		}
	}

}
