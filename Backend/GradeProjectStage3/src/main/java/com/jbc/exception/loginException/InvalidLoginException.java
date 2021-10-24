package com.jbc.exception.loginException;

import com.jbc.exception.CustomException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.serviceUtil.UserTypeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a failed
 * login attempt in the {@link com.jbc.service.LoginManagerService}
 * {@code Component}, {@code login} method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerService
 * @see exception#CustomException
 */
public final class InvalidLoginException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -2182762866749059906L;

	/* attributes */
	private UserTypeUtil userType;
	private String email;
	private boolean emailExists;

	/* constructor */
	public InvalidLoginException(String email, boolean emailExists, UserTypeUtil userType) {
		super.errorCode = ExceptionErrorCodeUtil.InvalidLoginException.toString();
		this.email = email;
		this.emailExists = emailExists;
		this.userType = userType;
	}

	/* toString */
	@Override
	public String toString() {
		if (userType.equals(UserTypeUtil.ADMIN))
			return "An Admin with the email and/or password doesn't exist, make sure the information is correct.";
		if (!emailExists) {
			return "A user with the email: " + email
					+ " doesn't exist, make sure the email is correct or create a new account.";
		}
		return "The password is incorrect, try again.";
	}

}
