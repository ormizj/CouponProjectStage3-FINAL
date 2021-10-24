package com.jbc.exception.loginException;

import com.jbc.exception.CustomException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * {@code null} in the {@link com.jbc.service.LoginManagerService}
 * {@code Component} {@code login} method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerService
 * @see exception#CustomException
 */
public final class NullLoginException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -9138544192447606819L;

	/* constructor */
	public NullLoginException() {
		super.errorCode = ExceptionErrorCodeUtil.NullLoginException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "The email, password or client value is null, make sure you enter all the needed information.";
	}

}
