package com.jbc.util.generalUtil;

/**
 * {@code class} which is used as an {@code enum} in order to synchronize the
 * system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
public class StringClass {

	/**
	 * {@code String} value that is used in a {@code synchronized} block, with
	 * {@code id} of a {@link com.jbc.model.Coupon} {@code Entity} to synchronize
	 * the coupons in the system.
	 * <p>
	 * <li><b>Example:</b> synchronized (StringClass.COUPON_ID_SYNC + couponId)
	 * {</li>
	 * <p>
	 * @see model#Coupon
	 */
	public static final String COUPON_ID_SYNC = "COUPON_ID_SYNC";

}
