package com.jbc.exception.couponException;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creating
 * or updating a {@link com.jbc.model.Coupon} {@code Entity} with a negative
 * stock value.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponExpiredException
 * @see model#Coupon
 */
public class CouponModifyNoStockException extends CouponNoStockException {

	/* serial */
	private static final long serialVersionUID = -1695511067874496587L;

	/* constructor */
	public CouponModifyNoStockException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponModifyNoStockException.toString();
		action = ExceptionUtil.UPDATE.name().toLowerCase() + " or " + ExceptionUtil.CREATE.name().toLowerCase();
		this.id = id;
	}

}
