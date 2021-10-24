package com.jbc.exception.couponException;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creating
 * or updating a {@link com.jbc.model.Coupon} {@code Entity} with a negative
 * price value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponException
 * @see model#Coupon
 */
public final class CouponNegativePriceException extends CouponException {

	/* serial */
	private static final long serialVersionUID = 7509044611898086927L;
	/* attributes */
	private double price;

	/* constructor */
	public CouponNegativePriceException(double price) {
		super.errorCode = ExceptionErrorCodeUtil.CouponNegativePriceException.toString();
		this.price = price;
	}

	/* toString */
	@Override
	public String toString() {
		return "The coupon price cannot be: " + price + ", the minimum value is: 0.";
	}

}
