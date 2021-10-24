package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a <b>searched</b> value of an {@code Entity} which was not found.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class NotFoundException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = -4992870382681308172L;
	protected long id;
	protected String entityName;
	protected String email;

	/* toString */
	@Override
	public String toString() {
		if (email != null) {
			return entityName + " email: " + email + " was not found, make sure that the " + entityName
					+ " email is correct.";
		}
		return entityName + " id: " + id + " was not found, make sure that the " + entityName + " id is correct.";
	}

}
