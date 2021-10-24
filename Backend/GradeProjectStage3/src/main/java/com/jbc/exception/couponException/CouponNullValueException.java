package com.jbc.exception.couponException;

import com.jbc.exception.generalException.NullValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} value in a {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NullValueException
 * @see model#Coupon
 */
public final class CouponNullValueException extends NullValueException {

	/* attributes */
	private static final long serialVersionUID = 3947556183985859582L;

	/* constructor */
	public CouponNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
