package com.jbc.exception.generalException;

import com.jbc.exception.CustomException;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a {@code null} value in an {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class NullValueException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -4344357858102499390L;
	/* attributes */
	protected byte numOfNull;
	protected ExceptionUtil nullName;
	protected String entityName;
	protected String nullsString;

	/**
	 * Creates a {@code String} made from the entered
	 * {@link com.jbc.util.exceptionUtil.ExceptionUtil} types entered, works
	 * accumulatively.
	 * 
	 * @param nameOfNull
	 * @see util#ExceptionUtil
	 */
	public void addNull(ExceptionUtil nameOfNull) {
		numOfNull++;
		if (nullsString == null) {
			nullsString = nameOfNull.name().toLowerCase();
		} else {
			nullsString = nullsString + ", " + nameOfNull.name().toLowerCase();
		}
	}

	/* toString */
	@Override
	public String toString() {
		return "You cannot have any \"null\" values in the " + entityName + ", you didn't enter a value for "
				+ numOfNull + " values which are " + nullsString + ".";
	}

}
