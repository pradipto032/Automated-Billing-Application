package com.craftMarine.authorization.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final int statusCode;
	private final HttpStatus status;
	private final String data;


}
