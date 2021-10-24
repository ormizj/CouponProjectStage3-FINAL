package com.jbc.service.user.ifc;

import java.util.List;
import java.util.Optional;

import com.jbc.exception.UserEmailAlreadyExists;
import com.jbc.exception.companyException.CompanyAlreadyExistsException;
import com.jbc.exception.companyException.CompanyDuplicateValueException;
import com.jbc.exception.companyException.CompanyIsEmptyException;
import com.jbc.exception.companyException.CompanyIsNullException;
import com.jbc.exception.companyException.CompanyNotFoundException;
import com.jbc.exception.companyException.CompanyNullValueException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.customerException.CustomerAlreadyExistsException;
import com.jbc.exception.customerException.CustomerDuplicateValueException;
import com.jbc.exception.customerException.CustomerIsEmptyException;
import com.jbc.exception.customerException.CustomerIsNullException;
import com.jbc.exception.customerException.CustomerNotFoundException;
import com.jbc.exception.customerException.CustomerNullValueException;
import com.jbc.exception.loggerException.LoggerIsEmptyException;
import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.model.user.Admin;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;

/**
 * {@code interface} that contains the methods used by the
 * {@link com.jbc.service.user.AdminService} {@code Service} to ensure the
 * business logic of the system, related to the {@link com.jbc.model.user.User}
 * type {@code Entity}s.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 * @see user#AdminService
 */
public interface AdminServiceIfc {

	/**
	 * 
	 * @param email
	 * @param password
	 * @return {@code Entity} of the {@code Admin} with the {@code email} and
	 *         {@code password}.
	 */
	public Optional<Admin> login(String email, String password);

	/**
	 * Creates a new {@link com.jbc.model.user.Company} {@code Entity} in the
	 * system.
	 * 
	 * @param adminId
	 * @param company
	 * @return the added {@code Company} {@code Entity}.
	 * @throws CompanyNullValueException      if the {@code Company} {@code Entity}
	 *                                        contains a {@code null} value.
	 * @throws CompanyIsNullException         if the {@code Company} value is
	 *                                        {@code null}.
	 * @throws CompanyDuplicateValueException if a {@code Company} {@code Entity}
	 *                                        with the same {@code name} or
	 *                                        {@code email} already exists in the
	 *                                        system.
	 * @throws CompanyAlreadyExistsException  if a {@code Company} {@code Entity}
	 *                                        with the same {@code id} already
	 *                                        exists in the system.
	 * @throws UserEmailAlreadyExists         if a user with the {@code Company}
	 *                                        {@code email} already exists.
	 * @see user#Company
	 */
	public Company createCompany(long adminId, Company company)
			throws CompanyNullValueException, CompanyIsNullException, CompanyDuplicateValueException,
			CompanyAlreadyExistsException, UserEmailAlreadyExists;

	/**
	 * 
	 * @return the {@code List} of {@link com.jbc.model.user.Company}
	 *         {@code Entity}s that exist in the system.
	 * @throws CompanyIsEmptyException if there is not a single {@code Company}
	 *                                 {@code Entity} in the system.
	 * @see user#Company
	 */
	public List<Company> getCompanies() throws CompanyIsEmptyException;

	/**
	 * 
	 * @param companyId
	 * @return the {@code Company} {@code Entity} with the {@code companyId}.
	 * @throws CompanyNotFoundException if a {@code Company} {@code Entity} with the
	 *                                  {@code companyId} does not exist in the
	 *                                  system.
	 * @see user#Company
	 */
	public Company getCompany(long companyId) throws CompanyNotFoundException;

	/**
	 * 
	 * @param email
	 * @return the {@code Company} {@code Entity} with the {@code email}.
	 * @throws CompanyNotFoundException if a {@code Company} {@code Entity} with the
	 *                                  {@code email} does not exist in the system.
	 * @see user#Company
	 */
	public Company getCompany(String email) throws CompanyNotFoundException;

	/**
	 * Updates a {@link com.jbc.model.user.Company} {@code Entity} that
	 * <b>exists</b> in the system.
	 * 
	 * @param adminId
	 * @param company
	 * @return the updated {@code Company} {@code Entity}.
	 * @throws CompanyNullValueException      if the {@code Company} {@code Entity}
	 *                                        has any {@code null} values in it.
	 * @throws CompanyIsNullException         {@code Company} value is {@code null}.
	 * @throws CompanyDuplicateValueException if a {@code Company} {@code Entity}
	 *                                        with the same {@code name} or
	 *                                        {@code email} already exists in the
	 *                                        system.
	 * @throws CompanyNotFoundException       if a {@code Company} {@code Entity}
	 *                                        with the {@code id} does not exist in
	 *                                        the system.
	 * @throws UserEmailAlreadyExists         if a user with the {@code Company}
	 *                                        {@code email} already exists.
	 * @see user#Company
	 */
	public Company updateCompany(long adminId, Company company) throws CompanyNullValueException,
			CompanyIsNullException, CompanyDuplicateValueException, CompanyNotFoundException, UserEmailAlreadyExists;

	/**
	 * Deletes a {@link com.jbc.model.user.Company} {@code Entity} based on the
	 * {@code companyId}.
	 * 
	 * @param adminId
	 * @param companyId
	 * @return
	 * @throws CompanyNotFoundException if a {@code Company} {@code Entity} with the
	 *                                  {@code companyId} does not exist in the
	 *                                  system.
	 * @see user#Company
	 */
	public void deleteCompany(long adminId, long companyId) throws CompanyNotFoundException;

	/**
	 * Adds a new {@link com.jbc.model.user.Customer} {@code Entity} to the system.
	 * 
	 * @param adminId
	 * @param customer
	 * @return the added {@code Customer} {@code Entity}.
	 * @throws CustomerAlreadyExistsException  if a {@code Customer} {@code Entity}
	 *                                         with the same {@code id}
	 *                                         {@code already exists}.
	 * @throws CustomerDuplicateValueException if a {@code Customer} {@code Entity}
	 *                                         with the same {@code email} already
	 *                                         exists in the system.
	 * @throws CustomerIsNullException         if the {@code Customer} value is
	 *                                         {@code null}
	 * @throws UserEmailAlreadyExists          if a user with the {@code Customer}
	 *                                         {@code email} already exists.
	 * @throws CustomerNullValueExceptio       if the {@code Customer}
	 *                                         {@code Entity} has any {@code null}
	 *                                         values in it.
	 * @see user#Customer
	 */
	public Customer addCustomer(long adminId, Customer customer)
			throws CustomerAlreadyExistsException, CustomerDuplicateValueException, CustomerIsNullException,
			CustomerNullValueException, UserEmailAlreadyExists;

	/**
	 * 
	 * @return the {@code List} of the {@link com.jbc.model.user.Customer}
	 *         {@code Entity}s that exist in the system.
	 * @throws CustomerIsEmptyException if there is not a single {@code Customer}
	 *                                  {@code Entity} in the system.
	 * @see user#Customer
	 */
	public List<Customer> getCustomers() throws CustomerIsEmptyException;

	/**
	 * 
	 * @param customerId
	 * @return the {@code Customer} {@code Entity} with the {@code customerId}.
	 * @throws CustomerNotFoundException if a {@code Customer} {@code Entity} with
	 *                                   the {@code customerId} does not exist in
	 *                                   the system.
	 * @see user#Customer
	 */
	public Customer getCustomer(long customerId) throws CustomerNotFoundException;

	/**
	 * 
	 * @param email
	 * @return the {@code Customer} {@code Entity} with the {@code email}.
	 * @throws CustomerNotFoundException if a {@code Customer} {@code Entity} with
	 *                                   the {@code email} does not exist in the
	 *                                   system.
	 * @see user#Customer
	 */
	public Customer getCustomer(String email) throws CustomerNotFoundException;

	/**
	 * Updates a {@link com.jbc.model.user.Customer} {@code Entity} that
	 * <b>exists</b> in the system.
	 * 
	 * @param adminId
	 * @param customer
	 * @return the updated {@code Customer} {@code Entity}.
	 * @throws CustomerDuplicateValueException if a {@code Customer} {@code Entity}
	 *                                         with the same {@code email} already
	 *                                         exists in the system.
	 * @throws CustomerIsNullException         if the {@code Customer} value is
	 *                                         {@code null}
	 * @throws CustomerNullValueException      if the {@code Customer}
	 *                                         {@code Entity} has any {@code null}
	 *                                         values in it.
	 * @throws CustomerNotFoundException       if a {@code Customer} {@code Entity}
	 *                                         with the {@code id} does not exist in
	 *                                         the system.
	 * @throws UserEmailAlreadyExists          if a user with the {@code Customer}
	 *                                         {@code email} already exists.
	 * @see user#Customer
	 */
	public Customer updateCustomer(long adminId, Customer customer) throws CustomerDuplicateValueException,
			CustomerIsNullException, CustomerNullValueException, CustomerNotFoundException, UserEmailAlreadyExists;

	/**
	 * Deletes a {@link com.jbc.model.user.Customer} {@code Entity} based on the
	 * {@code customeryId}.
	 * 
	 * @param adminId
	 * @param customerId
	 * @throws CustomerNotFoundException if a {@code Customer} {@code Entity} with
	 *                                   the {@code customerId} does not exist in
	 *                                   the system.
	 * @see user#Customer
	 */
	public void deleteCustomer(long adminId, long customerId) throws CustomerNotFoundException;

	/**
	 * 
	 * @return the {@code List} of the {@link com.jbc.model.Coupon} {@code Entity}s
	 *         that exist in the system.
	 * @throws CouponIsEmptyException if there is not a single {@code Coupon}
	 *                                {@code Entity} in the system.
	 * @see model#Coupon
	 */
	public List<Coupon> getCoupons() throws CouponIsEmptyException;

	/**
	 * 
	 * @return the {@code List} of the {@link com.jbc.model.Logger} {@code Entity}s
	 *         that exist in the system, aka: logs.
	 * @throws LoggerIsEmptyException if there is not a single {@code Logger}
	 *                                {@code Entity} in the system, aka: log.
	 * @see model#Logger
	 */
	public List<Logger> getLogs() throws LoggerIsEmptyException;

	/**
	 * Deletes all the {@link com.jbc.model.Logger} {@code Entity}s in the system,
	 * aka: logs.
	 * 
	 * @param adminId
	 * @throws LoggerIsEmptyException if there is not a single {@code Logger}
	 *                                {@code Entity} in the system, aka: log.
	 * @see model#Logger
	 */
	public void clearLogs(long adminId) throws LoggerIsEmptyException;

}
