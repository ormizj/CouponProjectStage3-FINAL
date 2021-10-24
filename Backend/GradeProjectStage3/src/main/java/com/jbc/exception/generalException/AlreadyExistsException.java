package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to creation of an {@code Entity} which {@code id} already exists in
 * the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class AlreadyExistsException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 8495176260690798221L;
	/* attributes */
	protected long id;
	protected String entityName;

	/* toString */
	@Override
	public String toString() {
		return "The " + entityName + " with the id " + id + " already exists, try creating a \"new\" " + entityName
				+ ".";
	}
}
