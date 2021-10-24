package com.jbc.exception.customerException;

import com.jbc.exception.generalException.NotFoundException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * <b>searched</b> value of a {@link com.jbc.model.user.Customer} {@code Entity}
 * which was not found.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NotFoundException
 * @see user#Customer
 */
public final class CustomerNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = -5686637033251638610L;

	/* constructors */
	public CustomerNotFoundException(long id) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.id = id;
	}

	public CustomerNotFoundException(String email) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.email = email;
	}
	

	public void setErrorCode() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerNotFoundException.toString();
	}

}
