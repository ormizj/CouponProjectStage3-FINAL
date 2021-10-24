package com.jbc.exception.loginException;

import com.jbc.exception.CustomException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * mismatch in the {@link com.jbc.model.user.User} related to the
 * {@link com.jbc.util.serviceUtil.UserTypeUtil}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public class MismatchLoginException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 6938953722353707073L;

	/* constructor */
	public MismatchLoginException() {
		super.errorCode = ExceptionErrorCodeUtil.MismatchLoginException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "An account exists with the entered credentials but as a different user type.";
	}
}
