package com.jbc.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbc.model.Coupon;
import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code Company} {@code Entity} that is using the {@link User} {@code Entity},
 * and the {@link Coupon} Entity}.
 * <p>
 * This {@code Entity} used for the creating the companies in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 * @see model#Coupon
 */
@Getter
@Setter
@Entity
@Table(name = "companies")
@ApiModel(description = "Email between users must be uinque.\nname between Companies must be unique.\n\nOwns and manages coupons.")
public class Company extends User {

	/* attributes */
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	@ApiModelProperty(value = "Only as response.")
	private List<Coupon> coupons;

	@Column(nullable = false, unique = true)
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String name;

	/* constructors */
	public Company() {
		role = UserTypeUtil.COMPANY;
	}

	public Company(String name, String email, String password) {
		coupons = new ArrayList<Coupon>();
		role = UserTypeUtil.COMPANY;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/* toString */
	@Override
	public String toString() {
		String string = "Company(id=" + id + ", name=" + name + ", email=" + email + ", password=" + password;
		if (coupons.isEmpty())
			return string + ", coupons=None)";
		return string + ", coupons=" + coupons + ")";
	}

}
