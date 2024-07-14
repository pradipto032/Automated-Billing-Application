package com.craftMarine.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Generated;

@ControllerAdvice
@Generated
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<String> memberNotFoundException (MemberNotFoundException memberNotFoundException){
		String response = memberNotFoundException.getData();
		return new ResponseEntity<>(response,HttpStatus.valueOf(memberNotFoundException.getStatusCode()));
	}
	
}
