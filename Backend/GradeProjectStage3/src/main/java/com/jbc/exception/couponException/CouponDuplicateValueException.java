package com.jbc.exception.couponException;

import com.jbc.exception.generalException.DuplicateValueException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to a {@link com.jbc.model.Coupon} {@code Entity} which unique values already exists
 * in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#DuplicateValueException
 * @see model#Coupon
 */
public final class CouponDuplicateValueException extends DuplicateValueException {

	/* serial */
	private static final long serialVersionUID = -6334847561117102189L;

	/* constructor */
	public CouponDuplicateValueException(String couponTitle) {
		super.errorCode = ExceptionErrorCodeUtil.CouponDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		duplicatesString = ExceptionUtil.TITLE.name().toLowerCase();
		this.duplicateValue = couponTitle;
	}
}
