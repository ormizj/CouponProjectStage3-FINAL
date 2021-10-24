package com.jbc.message.request.user;

import com.jbc.model.user.Customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link com.jbc.model.user.Customer} type {@code class} used for
 * {@code Controller} requests.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Customer
 * @see user#UserRequest
 */
@Getter
@Setter
public class CustomerRequest extends UserRequest {

	/* attributes */
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String firstName;
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String lastName;

	/**
	 * 
	 * @return {@code CustomerRequest} casted to a {@code Customer}.
	 */
	public Customer toCustomer() {
		Customer customer = new Customer(firstName, lastName, email, password);
		customer.setId(id);
		return customer;
	}

}
