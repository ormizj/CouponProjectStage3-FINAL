package com.jbc.util.serviceUtil.loggerUtil;

import com.jbc.model.user.Customer;

/**
 * {@code interface} containing {@code default} methods related to the
 * attributes of a {@link com.jbc.model.user.Customer} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Customer
 */
public interface CustomerLoggerAttributer {

	/**
	 * 
	 * @param company
	 * @return the attributes of the {@code Customer} as a {@code String}.
	 */
	default public String attributeCustomer(Customer customer) {
		return "First Name: " + customer.getFirstName() + ", Last Name: " + customer.getLastName() + ", Email: "
				+ customer.getEmail();
	}

}
