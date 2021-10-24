package com.jbc.exception.couponException;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * exceptions that are <b>specifically</b> created for the
 * {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see model#Coupon
 */
public abstract class CouponException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = -148510015680968765L;
	protected long id;
	
	/* toString */
	@Override
	public String toString() {
		return "Coupon id: " + id;
	}

}
