package com.jbc.exception.couponException;

import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to the
 * purchase of a {@link com.jbc.model.Coupon} {@code Entity} which is out of
 * stock.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see couponException#CouponNoStockException
 * @see model#Coupon
 */
public class CouponPurchaseNoStockException extends CouponNoStockException {

	/* serial */
	private static final long serialVersionUID = 1079138568606931980L;

	/* constructor */
	public CouponPurchaseNoStockException(long id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponPurchaseNoStockException.toString();
		action = ExceptionUtil.PURCHASE.name().toLowerCase();
		this.id = id;
	}

}
