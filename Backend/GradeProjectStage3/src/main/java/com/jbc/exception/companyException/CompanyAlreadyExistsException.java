package com.jbc.exception.companyException;

import com.jbc.exception.generalException.AlreadyExistsException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.user.Company} {@code Entity} which {@code id}
 * already exists in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#AlreadyExistsException
 * @see user#Company
 */
public final class CompanyAlreadyExistsException extends AlreadyExistsException {

	/* serial */
	private static final long serialVersionUID = 9051665933246097577L;

	/* constructor */
	public CompanyAlreadyExistsException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CompanyAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.id = id;
	}

}
