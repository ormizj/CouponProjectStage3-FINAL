package com.jbc.util.systemUtil;

/**
 * {@code enum} containing the number of {@code MINUTES} the
 * {@link com.jbc.system.CouponExpirationDailyJob} thread will attempt to shut
 * down.
 * <p>
 * <li>The method {@code toInt} should be used after specifying which
 * information is wanted, in order to get the corresponding information.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#CouponExpirationDailyJob
 * @see #toInt()
 */
public enum TerminationWaitTime {

	/* attributes */
	TIME(15);

	private final int MINUTES;

	/* constructor */
	TerminationWaitTime(int minutes) {
		MINUTES = minutes;
	}

	/* method */
	public int toInt() {
		return MINUTES;
	}

}
