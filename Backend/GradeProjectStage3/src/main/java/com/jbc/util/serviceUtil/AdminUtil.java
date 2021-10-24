package com.jbc.util.serviceUtil;

/**
 * {@code enum} containing the default username and password of the admin.
 * <p>
 * <li>The method {@code toString} should be used after specifying which
 * information is wanted, in order to get the corresponding information.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#AdminService
 * @see #toString()
 */
public enum AdminUtil {

	/* attributes */
	ADMIN_USER, ADMIN_PASS;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ADMIN_USER:
			return "admin@admin.com";
		case ADMIN_PASS:
			return "admin";
		default:
			return super.toString();
		}
	}

}
