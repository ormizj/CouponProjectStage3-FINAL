package com.jbc.exception.customerException;

import com.jbc.exception.generalException.IsNullException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@link com.jbc.model.user.Customer} {@code Entity}
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsNullException
 * @see user#Customer
 */
public final class CustomerIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 6062395087108093928L;

	/* constructor */
	public CustomerIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
	}

}
