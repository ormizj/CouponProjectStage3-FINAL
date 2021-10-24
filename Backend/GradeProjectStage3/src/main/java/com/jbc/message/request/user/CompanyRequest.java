package com.jbc.message.request.user;

import com.jbc.model.user.Company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link com.jbc.model.user.Company} type {@code class} used for
 * {@code Controller} requests.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Company
 * @see user#UserRequest
 */
@Getter
@Setter
public class CompanyRequest extends UserRequest {

	/* attributes */
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String name;

	/**
	 * 
	 * @return {@code CompanyRequest} casted to a {@code Company}.
	 */
	public Company toCompany() {
		Company company = new Company(name, email, password);
		company.setId(id);
		return company;
	}
}
