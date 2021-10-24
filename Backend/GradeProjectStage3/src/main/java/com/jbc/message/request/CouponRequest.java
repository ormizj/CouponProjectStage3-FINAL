package com.jbc.message.request;

import java.sql.Date;

import com.jbc.model.Coupon;
import com.jbc.util.modelUtil.CategoryUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link com.jbc.model.Coupon} type {@code class} used for
 * {@code Controller} requests.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Coupon
 */
@Getter
@Setter
public class CouponRequest {

	/*attributes*/
	@ApiModelProperty(value = "Auto-incremented.", example = "0")
	private long id;
	private CategoryUtil category;
	@ApiModelProperty(example = "yyyy-mm-dd", value = "Cannot be after \"endDate\".")
	private Date startDate;
	@ApiModelProperty(example = "yyyy-mm-dd", value = "Cannot be in the past.")
	private Date endDate;
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String title;
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String description;
	@ApiModelProperty(value = "Min=0, Max=2147483647.", example = "0")
	private int amount;
	@ApiModelProperty(value = "Min=0, Max=∞.", example = "0")
	private double price;
	@ApiModelProperty(value = "Min=1, Max=1000 (spaces gets removed).")
	private String image;

	/**
	 * 
	 * @return {@code CouponRequest} casted to a {@code Coupon}.
	 */
	public Coupon toCoupon() {
		Coupon coupon = new Coupon(category, title, description, startDate, endDate, amount, price, image);
		coupon.setId(id);
		return coupon;
	}
}
