package com.craftMarine.authorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.craftMarine.authorization.entities.MyUserDetails;
import com.craftMarine.authorization.entities.User;
import com.craftMarine.authorization.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}

		Optional<MyUserDetails> map = user.map(MyUserDetails::new);
		if (!map.isPresent()) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}

		return map.get();
	}

}
