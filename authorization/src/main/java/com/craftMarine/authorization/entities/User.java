package com.craftMarine.authorization.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private String id;
	
	private String userName;
	
	private String password;
	
	private String roles;
}
