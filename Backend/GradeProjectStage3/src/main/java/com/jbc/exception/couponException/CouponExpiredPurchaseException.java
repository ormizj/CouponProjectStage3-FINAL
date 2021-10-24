package com.jbc.exception.couponException;

import java.util.Date;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * purchasing a {@link com.jbc.model.Coupon} {@code Entity} with an expired date
 * value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponExpiredException
 * @see model#Coupon
 */
public final class CouponExpiredPurchaseException extends CouponExpiredException {

	/* attributes */
	private static final long serialVersionUID = 218981284770480943L;

	/* constructor */
	public CouponExpiredPurchaseException(long id, Date date) {
		super.errorCode = ExceptionErrorCodeUtil.CouponExpiredPurchaseException.toString();
		this.id = id;
		this.date = date;
		this.action = ExceptionUtil.PURCHASE.name().toLowerCase();
	}

}
