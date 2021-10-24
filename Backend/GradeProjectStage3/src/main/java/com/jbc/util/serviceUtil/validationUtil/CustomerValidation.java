package com.jbc.util.serviceUtil.validationUtil;

import com.jbc.exception.customerException.CustomerIsNullException;
import com.jbc.exception.customerException.CustomerNullValueException;
import com.jbc.model.user.Customer;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * {@code interface} containing {@code default} methods related to the
 * validation of a {@link com.jbc.model.user.Customer} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Customer
 */
public interface CustomerValidation extends StringModifier {

	/**
	 * Method to validate that a {@link com.jbc.model.user.Customer} {@code Entity}
	 * is not {@code null}, and doesn't have any {@code null} values.
	 * 
	 * @param customer
	 * @throws CustomerNullValueException if the {@code Customer} {@code Entity}
	 *                                    contains any values which are null.
	 * @throws CustomerIsNullException    if the {@code Customer} {@code Entity} is
	 *                                    {@code null}.
	 */
	public default void customerNullValidation(Customer customer)
			throws CustomerIsNullException, CustomerNullValueException {
		if (customer == null)
			throw new CustomerIsNullException();
		boolean firstNameException = customer.getFirstName() == null;
		if (!firstNameException) {
			customer.setFirstName(trim(customer.getFirstName()));
			firstNameException = customer.getFirstName().isEmpty();
		}
		boolean lastNameException = customer.getLastName() == null;
		if (!lastNameException) {
			customer.setLastName(trim(customer.getLastName()));
			lastNameException = customer.getLastName().isEmpty();
		}
		boolean emailException = customer.getEmail() == null;
		if (!emailException) {
			customer.setEmail(removeSpace(customer.getEmail()).toLowerCase());
			emailException = !isEmail(customer.getEmail());
		}
		boolean passwordException = customer.getPassword() == null;
		if (!passwordException)
			passwordException = customer.getPassword().isEmpty();
		if (firstNameException || lastNameException || emailException || passwordException) {
			CustomerNullValueException exception = new CustomerNullValueException();
			if (firstNameException)
				exception.addNull(ExceptionUtil.FIRST_NAME);
			if (lastNameException)
				exception.addNull(ExceptionUtil.LAST_NAME);
			if (emailException)
				if (customer.getEmail() == null)
					exception.addNull(ExceptionUtil.EMAIL);
				else
					exception.addNull(ExceptionUtil.INVALID_EMAIL_FORMAT);
			if (passwordException)
				exception.addNull(ExceptionUtil.PASSWORD);
			throw exception;
		}
	}

}
