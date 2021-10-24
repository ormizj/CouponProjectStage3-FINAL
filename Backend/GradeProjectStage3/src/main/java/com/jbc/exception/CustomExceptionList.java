package com.jbc.exception;

import java.util.ArrayList;
import java.util.List;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * {@code Exception} {@code class} which can contain multiple of
 * {@link com.jbc.exception.CustomException} type {@code Exception} as a
 * {@code List}, all exceptions throws can be printed by using the
 * {@code toString} or the {@code printStackTrace} methods.
 * 
 * *
 * <li>This exception should be instantiated and the method {@code addException}
 * should be called upon to add a {@link com.jbc.exception.CustomException} type
 * {@code Exception} to the {@code List}.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public class CustomExceptionList extends CustomException {

	/* serial */
	private static final long serialVersionUID = 4139293387609694779L;
	/* attributes */
	private List<CustomException> exceptions;

	/* constructor */
	public CustomExceptionList() {
		super.errorCode=ExceptionErrorCodeUtil.CustomExceptionList.toString();
		exceptions = new ArrayList<>();
	}

	/**
	 * Adds a {@link com.jbc.exception.CustomException} type {@code Exception} to
	 * the {@code exceptions} {@code List}.
	 * 
	 * @param exception
	 */
	public void addException(CustomException exception) {
		exceptions.add(exception);
	}

	/* toString */
	@Override
	public String toString() {
		String toString = "";
		for (CustomException customException : exceptions) {
			toString += customException.toString() + "\n";
		}
		return toString.substring(0,toString.length()-1);
	}
	
}
