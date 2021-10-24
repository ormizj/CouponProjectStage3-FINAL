package com.jbc.system;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbc.exception.CustomException;
import com.jbc.model.Coupon;
import com.jbc.model.user.Admin;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.repository.user.UserRepository;
import com.jbc.service.LoginManagerService;
import com.jbc.service.user.ifc.AdminServiceIfc;
import com.jbc.service.user.ifc.CompanyServiceIfc;
import com.jbc.service.user.ifc.CustomerServiceIfc;
import com.jbc.util.modelUtil.CategoryUtil;
import com.jbc.util.systemUtil.DatabaseCreator;

/**
 * {@code Component} which contains a {@link #testAll()} method for the testing
 * of the services, to ensure that their methods correctly.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see LoginManagerService
 * @see user#AdminService
 * @see user#CompanyService
 * @see user#CustomerService
 */
@Component
public class Test {

	/* attributes */
	@Autowired
	private AdminServiceIfc adminServ;
	@Autowired
	private CompanyServiceIfc companyServ;
	@Autowired
	private CustomerServiceIfc customerServ;
	@Autowired
	UserRepository adminRepository;

	/**
	 * Calls all the methods in the {@link com.jbc.service.user.AdminService},
	 * {@link com.jbc.service.user.CompanyService},
	 * {@link com.jbc.service.user.CustomerService} {@code Service}s, this method
	 * will not affect the system data-base, all the created {@code Entity}s are
	 * deleted at the end of the method.
	 * <p>
	 * <li>Prints an exception, if there was a problem with one of the
	 * {@code Service}s mentioned above.</li>
	 * <p>
	 * 
	 * @see service#LoginManager
	 * @see user#AdminService
	 * @see user#CompanyService
	 * @see user#CustomerService
	 */
	public void testAll(boolean test) {
		if (test) {
			System.out.println("\nStarting the test...\n");
			try {
				/* Creating a database for testings, if it doesn't exists */
				try {
					adminServ.getCustomers();
				} catch (CustomException e) {
					DatabaseCreator.createDatabase(adminServ, companyServ, customerServ);
				}

				/* Admin Login */
				Admin admin = new Admin("test", "test");
				System.out.println(admin);
				/* AdminService methods */
				/* AdminService Company methods */
				adminServ.createCompany(admin.getId(), new Company("Mamma Off", "Mamma@gmail.com", "Olamnizu1234"));
				List<Company> companies = adminServ.getCompanies();
				companies.forEach(System.out::println);
				Company updatedCompany = companies.get(companies.size() - 1);
				updatedCompany.setName("Bakara Inc");
				updatedCompany.setEmail("Bakara@gmail.com");
				updatedCompany.setPassword("a2801maIolz");
				adminServ.updateCompany(admin.getId(), updatedCompany);
				System.out.println(adminServ.getCompany(updatedCompany.getId()));
				System.out.println(adminServ.getCompany(updatedCompany.getEmail()));
				/* AdminService Customer methods */
				adminServ.addCustomer(admin.getId(),
						new Customer("Yonatan", "Caspi", "Caspi@gmail.com", "0128Moalizko"));
				List<Customer> customers = adminServ.getCustomers();
				customers.forEach(System.out::println);
				Customer updatedCustomer = customers.get(customers.size() - 1);
				updatedCustomer.setFirstName("Shay");
				updatedCustomer.setLastName("Dayan");
				updatedCustomer.setEmail("Dayan@gmail.com");
				updatedCustomer.setPassword("mopoa8zpL");
				adminServ.updateCustomer(admin.getId(), updatedCustomer);
				System.out.println(adminServ.getCustomer(updatedCustomer.getId()));
				System.out.println(adminServ.getCustomer(updatedCustomer.getEmail()));

				/* CompanyService methods */
				companyServ.addCoupon(updatedCompany.getId(), new Coupon(CategoryUtil.GAMING, "COD", "Free Game Copy",
						new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)),
						new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)), 19, 19.99, "GAMING.png"));
				List<Coupon> coupons = companyServ.getCompanyCoupons(updatedCompany.getId());
				coupons.forEach(System.out::println);
				Coupon updatedCoupon = coupons.get(coupons.size() - 1);
				updatedCoupon.setCategory(CategoryUtil.ELECTRICITY);
				updatedCoupon.setTitle("Microwave");
				updatedCoupon.setDescription("20% Discount on any microwave");
				updatedCoupon.setStartDate(new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 14)));
				updatedCoupon.setEndDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14)));
				updatedCoupon.setPrice(7.99);
				updatedCoupon.setAmount(20);
				updatedCoupon.setImage("ELECTRICITY.png");
				companyServ.updateCoupon(updatedCompany.getId(), updatedCoupon);
				companyServ.getCompanyCoupons(updatedCompany.getId()).forEach(System.out::println);
				companyServ.getCompanyCoupons(updatedCompany.getId(), 8).forEach(System.out::println);
				companyServ.getCompanyCoupons(updatedCompany.getId(), CategoryUtil.ELECTRICITY)
						.forEach(System.out::println);
				System.out.println(companyServ.getCompanyDetails(updatedCompany.getId()));
				adminServ.getCoupons().forEach(System.out::println);

				/* CustomerService methods */
				customerServ.purchaseCoupon(updatedCustomer.getId(), updatedCoupon.getId());
				customerServ.getCustomerCoupons(updatedCustomer.getId()).forEach(System.out::println);
				customerServ.getCustomerCoupons(updatedCustomer.getId(), 8).forEach(System.out::println);
				customerServ.getCustomerCoupons(updatedCustomer.getId(), CategoryUtil.ELECTRICITY)
						.forEach(System.out::println);
				System.out.println(customerServ.getCustomerDetails(updatedCustomer.getId()));

				/* Deleting updated entities */
				companyServ.deleteCoupon(updatedCompany.getId(), updatedCoupon.getId());
				adminServ.deleteCustomer(admin.getId(), updatedCustomer.getId());
				adminServ.deleteCompany(admin.getId(), updatedCompany.getId());

				System.out.println("\nTesting completed successfully.");
			} catch (CustomException e) {
				System.out.println("\nTesting was unsuccessful.");
				System.err.println(e);
			} finally {
				System.out.println("Test ended.\n");
			}
		}
	}
}
