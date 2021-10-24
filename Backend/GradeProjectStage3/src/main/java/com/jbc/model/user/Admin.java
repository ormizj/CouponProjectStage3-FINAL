package com.jbc.model.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code Admin} {@code Entity} that is using the {@link User} {@code Entity}.
 * <p>
 * This {@code Entity} used for the creating the admins in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 * @see model#Coupon
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "admins")
@ApiModel(value = "Admin", description = "Email between users must be unique.\n\nManages Companies.\nManages Customers\nManages Logs.")
public class Admin extends User {

	/* constructors */
	public Admin() {
		role = UserTypeUtil.ADMIN;
	}

	public Admin(String email, String password) {
		role = UserTypeUtil.ADMIN;
		this.email = email;
		this.password = password;
	}

}
