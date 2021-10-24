package com.jbc.exception.couponException;

import com.jbc.exception.generalException.IsNullException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsNullException
 * @see model#Coupon
 */
public final class CouponIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 2343329825146715443L;

	/* Constructor */
	public CouponIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
