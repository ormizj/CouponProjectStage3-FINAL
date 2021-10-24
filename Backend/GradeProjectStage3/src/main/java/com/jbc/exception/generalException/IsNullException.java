package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class IsNullException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 6918078131467279137L;
	/* attributes */
	protected String entityName;

	/* toString */
	@Override
	public String toString() {
		return "The " + entityName + " value is \"null\" or contains nulls, you need to initalize the " + entityName + " properly.";
	}
}
