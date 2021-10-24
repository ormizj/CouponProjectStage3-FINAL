package com.jbc.util.serviceUtil;

/**
 * {@code enum} containing the type of users that exist in the system.
 * <p>
 * <li>The method {@code toString} should be used after specifying which
 * information is wanted, in order to get the corresponding information (if used
 * for a string and not for the type).</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see #toString()
 */
public enum UserTypeUtil {

	/* attributes */
	ADMIN, COMPANY, CUSTOMER;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ADMIN:
			return "Admin";
		case COMPANY:
			return "Company";
		case CUSTOMER:
			return "Customer";
		default:
			return super.toString();
		}
	}

}
