package com.jbc.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jbc.model.Coupon;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@link com.jbc.model.Coupon} {@code JpaRepository} that is used to manage the
 * coupons in the system.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see model#Coupon
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	/* methods */
	/* Company queries */
	public Optional<Coupon> findByIdNotAndCompanyIdAndTitleIgnoreCase(long couponId, long companyId, String title);

	public Optional<Coupon> findByIdAndCompanyId(long couponId, long companyId);

	public List<Coupon> findByCompanyId(long companyId);

	public List<Coupon> findByCompanyIdAndCategory(long companyId, CategoryUtil couponCategory);

	public List<Coupon> findByCompanyIdAndPriceLessThanEqual(long companyId, double maxPrice);

	/* Customer queries */
	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.id = ?2")
	public Optional<Coupon> findByCustomerIdAndCouponId(long customerId, long couponId);

	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1")
	public Set<Coupon> findByCustomerId(long customerId);

	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.category = ?2")
	public Set<Coupon> findByCustomerIdAndCouponCategory(long customerId, CategoryUtil couponCategory);

	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.price <= ?2")
	public Set<Coupon> findByCustomerIdAndCouponPrice(long customerId, double maxPrice);

}
