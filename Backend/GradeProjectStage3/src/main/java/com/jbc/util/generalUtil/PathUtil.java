package com.jbc.util.generalUtil;

/**
 * {@code enum} which contains infromation related to paths and names related to
 * packages and {@code class}es.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see system#CouponExpirationDailyJob
 */
public enum PathUtil {

	/* attributes */
	COUPON_EXPIRATION_DAILY_JOB_NAME, COUPON_EXPIRATION_DAILY_JOB_PATH, COM_JBC_PACKAGE;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case COUPON_EXPIRATION_DAILY_JOB_NAME:
			return "CouponExpirationDailyJob";
		case COUPON_EXPIRATION_DAILY_JOB_PATH:
			return "com.jbc.system." + PathUtil.COUPON_EXPIRATION_DAILY_JOB_NAME.toString();
		case COM_JBC_PACKAGE:
			return "com.jbc";
		default:
			return super.toString();
		}
	}

}
