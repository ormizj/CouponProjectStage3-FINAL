package com.jbc.util.exceptionUtil;

/**
 * {@code enum} which contains information used by the exception {@code class}es
 * that extend the {@link com.jbc.exception.CustomException} {@code class}.
 * <p>
 * <li>The method {@code toString} should be used after specifying which
 * information is wanted, when using a "ENTITY_" type, in order to get the
 * correct information.</li>
 * <p>
 * The method {@code name} should be used when specifying a type that does not
 * contain the "CLASS_", to get the type name.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see #toString()
 */
public enum ExceptionUtil {

	/* attributes */
	ENTITY_COMPANY, ENTITY_COUPON, ENTITY_CUSTOMER, ENTITY_LOGGER,

	CATEGORY, TITLE, DESCRIPTION, START_DATE, END_DATE, IMAGE, NAME, FIRST_NAME, LAST_NAME, EMAIL,INVALID_EMAIL_FORMAT, PASSWORD, MAX_PRICE,
	PURCHASE, CREATE, ID, UPDATE;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ENTITY_COMPANY:
			return "Company";
		case ENTITY_COUPON:
			return "Coupon";
		case ENTITY_CUSTOMER:
			return "Customer";
		default:
			return super.toString();
		}
	}

}
