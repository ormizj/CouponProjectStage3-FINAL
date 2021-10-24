package com.jbc.exception;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * {@link com.jbc.model.user.User} {@code Entity} which {@code email} already
 * exists in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see CustomException
 * @see user#User
 */
public class UserEmailAlreadyExists extends CustomException {

	/* serial */
	private static final long serialVersionUID = 7409449700762540450L;
	/* attributes */
	private String email;

	/* constructor */
	public UserEmailAlreadyExists(String email) {
		super.errorCode = ExceptionErrorCodeUtil.UserEmailAlreadyExists.toString();
		this.email = email;
	}

	/* toString */
	@Override
	public String toString() {
		return "A user with the email \"" + email + "\" already exists, please choose a different email.";
	}

}
