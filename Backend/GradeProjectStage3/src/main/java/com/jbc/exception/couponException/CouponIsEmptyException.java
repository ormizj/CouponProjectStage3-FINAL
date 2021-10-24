package com.jbc.exception.couponException;

import com.jbc.exception.generalException.IsEmptyException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@link com.jbc.model.Coupon}'s which is empty.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#IsEmptyException
 * @see model#Coupon
 */
public class CouponIsEmptyException extends IsEmptyException {

	/* serial */
	private static final long serialVersionUID = 556323556945208243L;

	/* constructor */
	public CouponIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponIsEmptyException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
