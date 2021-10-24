package com.jbc.security.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jbc.model.user.User;

/**
 * UserPrinciple {@code class} for the Jwt Security system for implementing and
 * modifying the {@code UserDetails} interface.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerServiceIfc
 */
public class UserPrinciple implements UserDetails {

	/* serial */
	private static final long serialVersionUID = -8729678558012322165L;

	/* attributes */
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	/* constructor */
	public UserPrinciple(long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * 
	 * @param user
	 * @return a new instance of a {@code UserPrinciple} based on the inputed
	 *         {@code User}.
	 */
	public static UserPrinciple build(User user) {
		List<GrantedAuthority> newAuthorities = new ArrayList<>(
				Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
		return new UserPrinciple(user.getId(), user.getEmail(), user.getPassword(), newAuthorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * 
	 * @return The {@code UserPrinciple}'s id.
	 */
	public long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
