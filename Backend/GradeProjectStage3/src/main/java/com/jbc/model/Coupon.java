package com.jbc.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.util.modelUtil.CategoryUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code Coupon} {@code Entity} that is used by the {@link Company}
 * {@code Entity}, and the {@link Customer} {@code Entity}.
 * <p>
 * This {@code Entity} used for the creating the coupons in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#Company
 * @see user#Customer
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "coupons")
@ApiModel(description = "Title within the same Company must be unique.\n\nOwned and managed by Company.\nOwned by Customer.")
public class Coupon {

	/* attributes */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@ApiModelProperty(value = "Auto-incremented.")
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoryUtil category;

	@ManyToOne
	@ApiModelProperty(hidden = true)
	@JsonBackReference
	private Company company;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customersVsCoupons", joinColumns = @JoinColumn(name = "couponId"), inverseJoinColumns = @JoinColumn(name = "customerId"))
	@ApiModelProperty(hidden = true)
	@JsonBackReference
	private List<Customer> customers;

	@Column(nullable = false)
	@ApiModelProperty(example = "yyyy-mm-dd", value = "Cannot be after \"endDate\".")
	private Date startDate;
	@Column(nullable = false)
	@ApiModelProperty(example = "yyyy-mm-dd", value = "Cannot be in the past.")
	private Date endDate;
	@Column(nullable = false)
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String title;
	@Column(nullable = false)
	@ApiModelProperty(value = "Min=1, Max=255 (gets trimmed).")
	private String description;
	@ApiModelProperty(value = "Min=0, Max=2147483647.")
	private int amount;
	@ApiModelProperty(value = "Min=0, Max=∞.")
	private double price;
	@Column(nullable = false, length = 1000)
	@ApiModelProperty(value = "Min=1, Max=1000 (spaces gets removed).")
	private String image;

	/* constructor */
	public Coupon(CategoryUtil category, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		customers = new ArrayList<>();
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/* toString */
	@Override
	public String toString() {
		return "Coupon(id=" + id + ", category=" + category + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", title=" + title + ", description=" + description + ", amount=" + amount + ", price=" + price
				+ ", image=" + image + ", companyId=" + company.getId() + ")";
	}

}
