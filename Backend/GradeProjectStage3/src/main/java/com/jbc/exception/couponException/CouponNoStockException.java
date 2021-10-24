package com.jbc.exception.couponException;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * exceptions that are related to the {@link com.jbc.model.Coupon} {@code Entity} stock value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponException
 * @see model#Coupon
 */
public abstract class CouponNoStockException extends CouponException {

	/* attributes */
	private static final long serialVersionUID = -8169406035430103383L;
	protected String action;

	/* toString */
	@Override
	public String toString() {
		return super.toString() + " is out of stock, you can't " + action + " coupons that are out of stock.";
	}

}
