package com.jbc.exception.couponException;

import com.jbc.exception.generalException.NotFoundException;
import com.jbc.util.exceptionUtil.ExceptionErrorCodeUtil;
import com.jbc.util.exceptionUtil.ExceptionUtil;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * <b>searched</b> value of a {@link com.jbc.model.Coupon} {@code Entity}
 * which was not found.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalException#NotFoundException
 * @see model#Coupon
 */
public final class CouponNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = -2097523446869921554L;
	private CategoryUtil category;
	private double maxPrice;
	private ExceptionUtil toString;

	/* constructors */
	public CouponNotFoundException(long id) {
		setErrorCode();
		this.id = id;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.ID;
	}

	public CouponNotFoundException(CategoryUtil category) {
		setErrorCode();
		this.category = category;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.CATEGORY;
	}

	public CouponNotFoundException(double maxPrice) {
		setErrorCode();
		this.maxPrice = maxPrice;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.MAX_PRICE;
	}

	/* toString */
	@Override
	public String toString() {
		if (toString.equals(ExceptionUtil.CATEGORY)) {
			return entityName + " category: " + category + " was not found, make sure that the " + entityName
					+ " category is correct.";
		}
		if (toString.equals(ExceptionUtil.MAX_PRICE)) {
			return entityName + " max price: " + maxPrice + " was not found, make sure that the " + entityName
					+ " max price is correct.";
		}
		return super.toString();
	}
	

	public void setErrorCode() {
		super.errorCode = ExceptionErrorCodeUtil.CouponNotFoundException.toString();
	}
	
	

}
