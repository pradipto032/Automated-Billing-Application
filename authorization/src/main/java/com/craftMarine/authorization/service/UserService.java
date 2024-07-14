package com.craftMarine.authorization.service;

import org.springframework.http.ResponseEntity;

import com.craftMarine.authorization.request.LoginRequest;
import com.craftMarine.authorization.request.RegistrationRequest;
import com.craftMarine.authorization.response.LoginResponse;
import com.craftMarine.authorization.response.UserResponse;

public interface UserService {

	String register(RegistrationRequest registrationRequest);
	String login(LoginRequest loginRequest);
}
