package com.jbc.exception.companyException;

import com.jbc.exception.generalException.IsNullException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@link com.jbc.model.user.Company} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsNullException
 * @see user#Company
 */
public final class CompanyIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 4496682272249313607L;

	/* Constructor */
	public CompanyIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}
