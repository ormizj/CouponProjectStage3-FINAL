package com.jbc.exception.companyException;

import com.jbc.exception.generalException.NullValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} value in a {@link com.jbc.model.user.Company} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NullValueException
 * @see user#Company
 */
public final class CompanyNullValueException extends NullValueException {

	/* attributes */
	private static final long serialVersionUID = -6461212727416722187L;

	/* constructor */
	public CompanyNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}
