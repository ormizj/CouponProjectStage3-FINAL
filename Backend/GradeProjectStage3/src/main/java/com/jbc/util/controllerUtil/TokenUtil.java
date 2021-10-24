package com.jbc.util.controllerUtil;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jbc.security.services.UserPrinciple;

/**
 * {@code interface} containing {@code default} methods related to Jwt tokens.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
public interface TokenUtil {

	/**
	 * 
	 * @return The id of the logged in {@code User}.
	 */
	public default long getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long id = 0;
		if (principal instanceof UserPrinciple)
			id = ((UserPrinciple) principal).getId();
		return id;
	}

}
