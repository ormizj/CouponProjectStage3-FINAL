package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@code Entity}s which is empty.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class IsEmptyException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -3039016111589446069L;
	/* attributes */
	protected String entityName;

	/* toString */
	@Override
	public String toString() {
		return "No " + entityName + "'s exist, you need to add a " + entityName + " to the system.";
	}

}
