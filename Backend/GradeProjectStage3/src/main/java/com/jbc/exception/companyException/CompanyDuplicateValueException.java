package com.jbc.exception.companyException;

import com.jbc.exception.generalException.DuplicateValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * {@code Exception} {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a {@link com.jbc.model.user.Company} {@code Entity} which unique values already
 * exists in the system.
 * <p>
 * <li>This exception should be instantiated and the method {@code addDuplicate}
 * should be called upon for every value which is unique and already exists in
 * the system, with the help of the
 * {@link com.jbc.util.exceptionUtil.ExceptionUtil} to specify the values.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#DuplicateValueException
 * @see user#Company
 */
public final class CompanyDuplicateValueException extends DuplicateValueException {

	/* serial */
	private static final long serialVersionUID = -8022985052160567479L;

	/* constructor */
	public CompanyDuplicateValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

	/**
	 * Creates {@code String} made from the entered
	 * {@link com.jbc.util.exceptionUtil.ExceptionUtil} types entered, and the
	 * entered {@code String}, works accumulatively.
	 * 
	 * @param duplicateName
	 * @param duplicateValue
	 * @see util#ExceptionUtil
	 */
	public void addDuplicate(ExceptionUtil duplicateName, String duplicateValue) {
		if (duplicatesString == null) {
			duplicatesString = duplicateName.name().toLowerCase();
			this.duplicateValue = duplicateValue;
		} else {
			duplicatesString = duplicatesString + ", " + duplicateName.name().toLowerCase();
			this.duplicateValue = this.duplicateValue + ", " + duplicateValue;
		}
	}
}
