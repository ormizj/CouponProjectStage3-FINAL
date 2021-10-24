package com.jbc.exception.loggerException;

import com.jbc.exception.generalException.IsEmptyException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@link com.jbc.model.Logger}'s which is empty (no logs in the
 * system).
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsEmptyException
 * @see model#Logger
 */
public class LoggerIsEmptyException extends IsEmptyException {

	/* serial */
	private static final long serialVersionUID = -1979979622844195540L;

	/* constructor */
	public LoggerIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.LoggerIsEmptyException.toString();
	}

	@Override
	public String toString() {
		return "There are no logs in the system.";
	}

}
