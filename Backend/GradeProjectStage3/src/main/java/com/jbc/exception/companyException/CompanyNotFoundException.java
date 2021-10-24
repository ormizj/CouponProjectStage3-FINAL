package com.jbc.exception.companyException;

import com.jbc.exception.generalException.NotFoundException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * <b>searched</b> value of a {@link com.jbc.model.user.Company} {@code Entity}
 * which was not found.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NotFoundException
 * @see user#Company
 */
public final class CompanyNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = 4924233908046554183L;

	/* constructors */
	public CompanyNotFoundException(long id) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.id = id;
	}

	public CompanyNotFoundException(String email) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.email = email;
	}
	
	public void setErrorCode() {
		super.setErrorCode(ExceptionErrorCodeUtil.CompanyNotFoundException.toString());
	}

}
