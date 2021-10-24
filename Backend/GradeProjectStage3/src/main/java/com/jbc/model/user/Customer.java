package com.jbc.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbc.model.Coupon;
import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code Customer} {@code Entity} that is using the {@link User}
 * {@code Entity}, and the {@link Coupon} {@code Entity}.
 * <p>
 * This {@code Entity} used for the creating the customers in the system.
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
@Table(name = "customers")
@ApiModel(description = "Email between users must be uinque.\n\nOwns and purchases coupons.")
public class Customer extends User {

	/* attributes */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "customersVsCoupons", joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "couponId"))
	@JsonManagedReference
	@ApiModelProperty(value = "Only as response.")
	private Set<Coupon> coupons;

	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	@Column(nullable = false)
	private String firstName;
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	@Column(nullable = false)
	private String lastName;

	/* constructors */
	public Customer() {
		role = UserTypeUtil.CUSTOMER;
	}

	public Customer(String firstName, String lastName, String email, String password) {
		coupons = new HashSet<>();
		role = UserTypeUtil.CUSTOMER;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/* toString */
	@Override
	public String toString() {
		String string = "Customer(id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", email="
				+ email + ", password=" + password;
		if (coupons.isEmpty())
			return string + ", coupons=None)";
		return string + ", coupons=" + coupons + ")";
	}

}
