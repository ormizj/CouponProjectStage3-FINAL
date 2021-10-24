package com.jbc.exception.couponException;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to the
 * purchase of a {@link com.jbc.model.Coupon} {@code Entity} which is already owned by the
 * {@link com.jbc.model.user.Customer} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponException
 * @see user#Customer
 * @see model#Coupon
 */
public final class CouponOwnedException extends CouponException {

	/* serial */
	private static final long serialVersionUID = 9010697755872441982L;

	/* constructor */
	public CouponOwnedException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponOwnedException.toString();
		this.id = id;
	}

	/* toString */
	@Override
	public String toString() {
		return super.toString() + " is already owned, you can't purchase already owned coupons.";
	}

}
