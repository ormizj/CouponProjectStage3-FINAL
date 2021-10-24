package com.jbc.util.generalUtil;

/**
 * {@code enum} containing the current timezone used by various {@code class}es
 * in the application.
 * <p>
 * <li>The method {@code toString} should be used after specifying which
 * information is wanted, in order to get the corresponding information.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see system#CouponExpirationDailyJob
 * @see #toString()
 */
public enum TimeZoneUtil {

	/* attributes */
	ISRAEL;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ISRAEL:
			return "Israel";
		default:
			return super.toString();
		}
	}

}
