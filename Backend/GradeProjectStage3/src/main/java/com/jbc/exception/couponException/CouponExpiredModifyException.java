package com.jbc.exception.couponException;

import java.util.Date;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creating
 * or updating a {@link com.jbc.model.Coupon} {@code Entity} with an expired
 * date value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponExpiredException
 * @see model#Coupon
 */
public final class CouponExpiredModifyException extends CouponExpiredException {

	/* serial */
	private static final long serialVersionUID = -746966472553091269L;

	/* constructor */
	public CouponExpiredModifyException(long id, Date date) {
		super.errorCode = ExceptionErrorCodeUtil.CouponExpiredModifyException.toString();
		this.id = id;
		this.date = date;
		action = ExceptionUtil.CREATE.name().toLowerCase() + " or " + ExceptionUtil.UPDATE.name().toLowerCase();
	}

}
