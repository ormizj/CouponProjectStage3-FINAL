package scrap.scrap.oldCode.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbc.exception.CustomException;
import com.jbc.model.user.User;
import com.jbc.service.LoginManagerService;
import com.jbc.service.user.ifc.AdminServiceIfc;
import com.jbc.service.user.ifc.CompanyServiceIfc;
import com.jbc.service.user.ifc.CustomerServiceIfc;
import com.jbc.util.serviceUtil.UserTypeUtil;
import com.jbc.util.systemUtil.DatabaseCreator;

/**
 * {@code Component} which contains a {@link #testAll()} method for the testing
 * of the services, to ensure that their methods correctly.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see service#LoginManagerService
 * @see service#user#AdminService
 * @see service#user#CompanyService
 * @see service#user#CustomerService
 */
@SuppressWarnings("unused")
@Component
public class AnotherTest {

	/* attributes */
	@Autowired
	private AdminServiceIfc adminServ;
	@Autowired
	private CompanyServiceIfc companyServ;
	@Autowired
	private CustomerServiceIfc customerServ;
	@Autowired
	private LoginManagerService loginManager;

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
	 * @see service#user#AdminService
	 * @see service#user#CompanyService
	 * @see service#user#CustomerService
	 */
	public void testAll() {
		System.out.println("\nStarting the test...\n");
		try {
			/* Creating a database for the system */
			DatabaseCreator.createDatabase(adminServ, companyServ, customerServ);

			/* Admin Login */
//			User admin = loginManager.login("admin@admin.com", "admin", UserTypeUtil.ADMIN);
//			System.out.println(admin);
			/* AdminService methods */
			/* AdminService Company methods */
//			adminServ.createCompany(admin.getId(), new Company("Mamma Off", "Mamma@gmail.com", "Olamnizu1234")); V
//			List<Company> companies = adminServ.getCompanies(); V
//			companies.stream().forEach(System.out::println); V
//			Company updatedCompany = companies.get(companies.size() - 1); V
//			updatedCompany.setName("Bakara Inc"); V
//			updatedCompany.setEmail("Bakara@gmail.com"); V
//			updatedCompany.setPassword("a2801maIolz"); V
//			Company tempCompany = new Company("updatedCompany", "updated@gmail.com", "test"); V
//			tempCompany.setId(2);  V
//			adminServ.updateCompany(admin.getId(), tempCompany); V
//			System.out.println(adminServ.getCompany(updatedCompany.getId())); V
//			System.out.println(adminServ.getCompany(updatedCompany.getEmail())); V
			/* AdminService Customer methods */
//			adminServ.addCustomer(admin.getId(), new Customer("Yonatan", "Caspi", "Caspi@gmail.com", "0128Moalizko")); V
//			List<Customer> customers = adminServ.getCustomers(); V
//			customers.stream().forEach(System.out::println); V
//			Customer updatedCustomer = customers.get(customers.size() - 1); V
//			updatedCustomer.setFirstName("Shay"); V
//			updatedCustomer.setLastName("Dayan"); V
//			updatedCustomer.setEmail("Dayan@gmail.com"); V
//			updatedCustomer.setPassword("mopoa8zpL"); V
//			Customer tempCustomer = new Customer("testfirst", "testlast", "testemail", "testpass"); V
//			tempCustomer.setId(12); V
//			adminServ.updateCustomer(admin.getId(), tempCustomer); V
//			System.out.println(adminServ.getCustomer(updatedCustomer.getId())); V
//			System.out.println(adminServ.getCustomer(updatedCustomer.getEmail())); V

			/* CompanyService methods */
//			companyServ.addCoupon(4, 
//					new Coupon(CategoryUtil.GAMING, "COD", "Free Game Copy",
//							new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)),
//							new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)), 19, 19.99, "GAMING.png")); V
//			List<Coupon> coupons = companyServ.getCompanyCoupons(4); V
//			coupons.stream().forEach(System.out::println); V
//			Coupon updatedCoupon = coupons.get(coupons.size() - 1); V
//			updatedCoupon.setCategory(CategoryUtil.ELECTRICITY); V
//			updatedCoupon.setTitle("Microwave"); V
//			updatedCoupon.setDescription("20% Discount on any microwave"); V
//			updatedCoupon.setStartDate(new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 14))); V
//			updatedCoupon.setEndDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14))); V
//			updatedCoupon.setPrice(7.99); V
//			updatedCoupon.setAmount(20); V
//			updatedCoupon.setImage("ELECTRICITY.png"); V
//			Coupon tempCoupon = new Coupon(CategoryUtil.GAMING, "dsfsdfzxdf", "Free Game Copy",
//					new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)),
//					new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)), 19, 19.99, "GAMING.png"); 
//			tempCoupon.setId(4); 
//			companyServ.updateCoupon(3, tempCoupon); 
//			companyServ.getCompanyCoupons(updatedCompany.getId()).stream().forEach(System.out::println); V
//			companyServ.getCompanyCoupons(updatedCompany.getId(), 8).stream().forEach(System.out::println); V
//			companyServ.getCompanyCoupons(updatedCompany.getId(), CategoryUtil.ELECTRICITY).stream() 
//					.forEach(System.out::println); V
//			System.out.println(companyServ.getCompanyDetails(updatedCompany.getId())); V
//			adminServ.getCoupons().stream().forEach(System.out::println); V

			/* CustomerService methods */
//			customerServ.purchaseCoupon(updatedCustomer.getId(), updatedCoupon.getId()); V
//			customerServ.getCustomerCoupons(updatedCustomer.getId()).stream().forEach(System.out::println); V
//			customerServ.getCustomerCoupons(updatedCustomer.getId(), 8).stream().forEach(System.out::println); V
//			customerServ.getCustomerCoupons(updatedCustomer.getId(), CategoryUtil.ELECTRICITY).stream()
//					.forEach(System.out::println); V
//			System.out.println(customerServ.getCustomerDetails(updatedCustomer.getId())); V

			/* LoginManager logins */
//		System.out.println(
//					loginManager.login(updatedCompany.getEmail(), updatedCompany.getPassword(), UserTypeUtil.COMPANY));
//			System.out.println(loginManager.login(updatedCustomer.getEmail(), updatedCustomer.getPassword(),
//					UserTypeUtil.CUSTOMER)); V

			/* Deleting updated entities */
//			companyServ.deleteCoupon(updatedCompany.getId(), updatedCoupon.getId());
//			adminServ.deleteCustomer(admin.getId(), updatedCustomer.getId());
//			adminServ.deleteCompany(admin.getId(), updatedCompany.getId());

			System.out.println("\nTesting completed successfully.");
		} catch (CustomException e) {
			System.out.println("\nTesting was unsuccessful.");
			System.err.println(e);
		} finally {
			System.out.println("Test ended.\n");
		}
	}
}