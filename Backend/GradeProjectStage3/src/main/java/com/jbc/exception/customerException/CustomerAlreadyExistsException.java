package com.jbc.exception.customerException;

import com.jbc.exception.generalException.AlreadyExistsException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.user.Customer} {@code Entity} which {@code id} already exists in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#AlreadyExistsException
 * @see user#Customer
 */
public final class CustomerAlreadyExistsException extends AlreadyExistsException {

	/* serial */
	private static final long serialVersionUID = 2638421074780523510L;

	/* constructor */
	public CustomerAlreadyExistsException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CustomerAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.id = id;
	}

}
