package com.jbc.exception.couponException;

import com.jbc.exception.generalException.AlreadyExistsException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.Coupon} {@code Entity} which {@code id} already exists in the
 * system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#AlreadyExistsException
 * @see model#Coupon
 */
public final class CouponAlreadyExistsException extends AlreadyExistsException {

	/* serial */
	private static final long serialVersionUID = 8381693827455066967L;

	/* constructor */
	public CouponAlreadyExistsException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		this.id = id;
	}

}
