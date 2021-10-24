package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to an {@code Entity} which values already exists in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class DuplicateValueException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 2023156129898996103L;
	/* attributes */
	protected String duplicatesString;
	protected String duplicateValue;
	protected String entityName;

	/* toString */
	@Override
	public String toString() {
		return duplicateValue + " already exists in the " + entityName + " system, you need to change your "
				+ duplicatesString + ".";
	}

}
