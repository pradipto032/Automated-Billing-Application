package com.craftMarine.authorization.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

	@NotBlank(message = "Name can't be blank")
	private String name;
	
	@NotBlank(message = "password can't be blank")
	private String password;
	
	@NotBlank(message= "role can't be blank")
	private String role;
	
}
