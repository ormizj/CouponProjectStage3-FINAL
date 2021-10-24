package com.jbc.util.serviceUtil.loggerUtil;

import com.jbc.model.user.Admin;

/**
 * {@code interface} containing {@code default} methods related to the
 * attributes of a {@link com.jbc.model.user.User} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 */
public interface AdminLoggerAttributer {

	/**
	 * 
	 * @param admin
	 * @return the attributes of the {@code User} as a {@code String}.
	 */
	default public String attributeAdmin(Admin admin) {
		return "Email: " + admin.getEmail();
	}

}
