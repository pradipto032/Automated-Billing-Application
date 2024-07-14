package com.craftMarine.authorization.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private String userName;
	private String password;
	private String roles;

	public MyUserDetails() {
		
	}
	
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(roles));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
