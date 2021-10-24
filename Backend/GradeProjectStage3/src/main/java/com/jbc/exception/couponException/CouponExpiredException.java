package com.jbc.exception.couponException;

import java.util.Date;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * exceptions that are related to the {@link com.jbc.model.Coupon}
 * {@code Entity} date value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponException
 * @see model#Coupon
 */
public abstract class CouponExpiredException extends CouponException {

	/* serial */
	private static final long serialVersionUID = 659136974186842471L;
	/* attributes */
	protected Date date;
	protected String action;
	
	/* toString */
	@Override
	public String toString() {
		return super.toString() + " has expired at: " + date + ", you can't " + action + " expired coupons.";
	}

}
