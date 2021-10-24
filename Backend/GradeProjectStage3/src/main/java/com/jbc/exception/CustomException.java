package com.jbc.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * custom exceptions that are customly created for the system, all exceptions
 * throws can be printed by using the {@code toString} or the
 * {@code printStackTrace} methods.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
@Getter
@Setter
public abstract class CustomException extends Exception {

	/* serial */
	private static final long serialVersionUID = 6196041472980746758L;
	
	/*attributes*/
	 protected String errorCode;

}
