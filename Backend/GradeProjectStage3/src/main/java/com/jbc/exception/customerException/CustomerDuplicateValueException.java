package com.jbc.exception.customerException;

import com.jbc.exception.generalException.DuplicateValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a {@link com.jbc.model.user.Customer} {@code Entity} which unique values already
 * exists in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#DuplicateValueException
 * @see user#Customer
 */
public final class CustomerDuplicateValueException extends DuplicateValueException {

	/* serial */
	private static final long serialVersionUID = -3439177959321226748L;

	/* constructor */
	public CustomerDuplicateValueException(String customerEmail) {
		super.errorCode = ExceptionErrorCodeUtil.CustomerDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		duplicatesString = ExceptionUtil.EMAIL.name().toLowerCase();
		this.duplicateValue = customerEmail;
	}
}
