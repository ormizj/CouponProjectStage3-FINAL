package com.jbc.util.serviceUtil.loggerUtil;

import com.jbc.model.user.Company;

/**
 * {@code interface} containing {@code default} methods related to the
 * attributes of a {@link com.jbc.model.user.Company} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Company
 */
public interface CompanyLoggerAttributer {

	/**
	 * 
	 * @param company
	 * @return the attributes of the {@code Company} as a {@code String}.
	 */
	default public String attributeCompany(Company company) {
		return "Name: " + company.getName() + ", Email: " + company.getEmail();
	}

}
