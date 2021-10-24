package com.jbc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jbc.repository.user.UserRepository;

/**
 * {@code Service} for the Jwt Security system for implementing and modifying
 * the {@code UserDetailsService}, and also creating {@code UserDetails}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	/* attributes */
	@Autowired
	UserRepository userRepo;

	/* methods */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserPrinciple.build(userRepo.findByEmailIgnoreCase(username).orElseThrow(
				() -> new UsernameNotFoundException("User with the email \"" + username + "\" was not found.")));
	}

}
