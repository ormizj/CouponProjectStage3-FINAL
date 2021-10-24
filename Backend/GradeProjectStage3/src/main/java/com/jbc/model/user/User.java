package com.jbc.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.jbc.util.serviceUtil.UserTypeUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code User} {@code Entity} that is used by the
 * {@link com.jbc.model.user.Admin} {@code Entity},
 * {@link com.jbc.model.user.Company} {@code Entity}, and the
 * {@link com.jbc.model.user.Customer} {@code Entity}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Admin
 * @see user#Company
 * @see user#Customer
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

	/* attributes */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value = "Auto-incremented.")
	protected long id;

	@Column(nullable = false, unique = true)
	@ApiModelProperty(example = "email@email.com", value = "Min=1, Max=255 (spaces gets removed).\nMust be in email format.\nGets lower-cased.")
	protected String email;
	@Column(nullable = false)
	@ApiModelProperty(value = "Min=1, Max=255.")
	protected String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected UserTypeUtil role;

}
