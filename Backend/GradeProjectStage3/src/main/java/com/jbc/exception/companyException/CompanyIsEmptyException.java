package com.jbc.exception.companyException;

import com.jbc.exception.generalException.IsEmptyException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@link com.jbc.model.user.Company}'s which is empty.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsEmptyException
 * @see user#Company
 */
public class CompanyIsEmptyException extends IsEmptyException {

	/* serial */
	private static final long serialVersionUID = 556323556945208243L;

	/* constructor */
	public CompanyIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyIsEmptyException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}