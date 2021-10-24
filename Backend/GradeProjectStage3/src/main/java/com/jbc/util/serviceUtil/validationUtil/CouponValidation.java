package com.jbc.util.serviceUtil.validationUtil;

import com.jbc.exception.CustomExceptionList;
import com.jbc.exception.couponException.CouponExpiredModifyException;
import com.jbc.exception.couponException.CouponIsNullException;
import com.jbc.exception.couponException.CouponModifyNoStockException;
import com.jbc.exception.couponException.CouponNegativePriceException;
import com.jbc.exception.couponException.CouponNullValueException;
import com.jbc.model.Coupon;
import com.jbc.util.exceptionUtil.ExceptionUtil;
import com.jbc.util.generalUtil.TimeComparisonUtil;

/**
 * {@code interface} containing {@code default} methods related to the
 * validation of a {@link com.jbc.model.Coupon} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Coupon
 */
public interface CouponValidation extends TimeComparisonUtil, StringModifier {

	/**
	 * Method to validate that a {@link com.jbc.model.Coupon} {@code Entity} is not
	 * {@code null}, and doesn't have any {@code null} values, {@code price} and
	 * {@code amount} is not negative, and the {@code endDate} is not already
	 * expired.
	 * 
	 * @param coupon
	 * @throws CouponNullValueException if the {@code Coupon} {@code Entity}
	 *                                  contains any values which are null.
	 * @throws CustomExceptionList      if multiple {@code Exception}s are possible
	 *                                  to occur at once.
	 * @throws CouponIsNullException    if the {@code Coupon} {@code Entity} is
	 *                                  {@code null}
	 */
	public default void couponNullValidation(Coupon coupon)
			throws CouponNullValueException, CustomExceptionList, CouponIsNullException {
		if (coupon == null)
			throw new CouponIsNullException();
		boolean titleException = coupon.getTitle() == null;
		if (!titleException) {
			coupon.setTitle(trim(coupon.getTitle()));
			titleException = coupon.getTitle().isEmpty();
		}
		boolean descException = coupon.getDescription() == null;
		if (!descException) {
			coupon.setDescription(trim(coupon.getDescription()));
			descException = coupon.getDescription().isEmpty();
		}
		boolean imageException = coupon.getImage() == null;
		if (!imageException) {
			coupon.setImage(removeSpace(coupon.getImage()));
			imageException = coupon.getImage().isEmpty();
		}
		if (coupon.getCategory() == null || titleException || descException || coupon.getStartDate() == null
				|| coupon.getEndDate() == null || imageException) {
			CouponNullValueException exception = new CouponNullValueException();
			if (coupon.getCategory() == null)
				exception.addNull(ExceptionUtil.CATEGORY);
			if (titleException)
				exception.addNull(ExceptionUtil.TITLE);
			if (descException)
				exception.addNull(ExceptionUtil.DESCRIPTION);
			if (coupon.getStartDate() == null)
				exception.addNull(ExceptionUtil.START_DATE);
			if (coupon.getEndDate() == null)
				exception.addNull(ExceptionUtil.END_DATE);
			if (imageException)
				exception.addNull(ExceptionUtil.IMAGE);
			throw exception;
		}
		if (coupon.getAmount() < 0 || coupon.getPrice() < 0 || isPast(coupon.getEndDate())
				|| coupon.getStartDate().after(coupon.getEndDate())) {
			CustomExceptionList exceptionList = new CustomExceptionList();
			if (coupon.getAmount() < 0)
				exceptionList.addException(new CouponModifyNoStockException(coupon.getId()));
			if (coupon.getPrice() < 0)
				exceptionList.addException(new CouponNegativePriceException(coupon.getPrice()));
			if (isPast(coupon.getEndDate()))
				exceptionList.addException(new CouponExpiredModifyException(coupon.getId(), coupon.getEndDate()));
			throw exceptionList;
		}
	}

}
