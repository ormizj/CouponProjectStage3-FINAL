package com.jbc.exception.customerException;

import com.jbc.exception.generalException.NullValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} value in a {@link com.jbc.model.user.Customer} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NullValueException
 * @see user#Customer
 */
public final class CustomerNullValueException extends NullValueException {

	/* serial */
	private static final long serialVersionUID = -633491210916568262L;

	/* constructor */
	public CustomerNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
	}

}
