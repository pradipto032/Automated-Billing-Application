package com.craftMarine.authorization.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.craftMarine.authorization.request.LoginRequest;
import com.craftMarine.authorization.request.RegistrationRequest;
import com.craftMarine.authorization.response.LoginResponse;
import com.craftMarine.authorization.response.UserResponse;
import com.craftMarine.authorization.service.UserService;

@RequestMapping("/bill/auth")
@RestController
public class AuthorizationController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}
	
	@PostMapping("/register")
	public String register(@RequestBody @Valid RegistrationRequest registrationRequest) {

		return userService.register(registrationRequest);
	}
	
}
