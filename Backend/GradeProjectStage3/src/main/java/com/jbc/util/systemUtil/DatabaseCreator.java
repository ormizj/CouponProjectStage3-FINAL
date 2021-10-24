package com.jbc.util.systemUtil;

import java.sql.Date;
import java.util.List;

import com.jbc.exception.CustomExceptionList;
import com.jbc.exception.UserEmailAlreadyExists;
import com.jbc.exception.companyException.CompanyDuplicateValueException;
import com.jbc.exception.companyException.CompanyIsNullException;
import com.jbc.exception.companyException.CompanyNotFoundException;
import com.jbc.exception.companyException.CompanyNullValueException;
import com.jbc.exception.couponException.CouponDuplicateValueException;
import com.jbc.exception.couponException.CouponIsEmptyException;
import com.jbc.exception.couponException.CouponIsNullException;
import com.jbc.exception.couponException.CouponNullValueException;
import com.jbc.exception.customerException.CustomerDuplicateValueException;
import com.jbc.exception.customerException.CustomerIsEmptyException;
import com.jbc.exception.customerException.CustomerIsNullException;
import com.jbc.exception.customerException.CustomerNotFoundException;
import com.jbc.exception.customerException.CustomerNullValueException;
import com.jbc.exception.generalException.AlreadyExistsException;
import com.jbc.model.Coupon;
import com.jbc.model.user.Company;
import com.jbc.model.user.Customer;
import com.jbc.service.user.ifc.AdminServiceIfc;
import com.jbc.service.user.ifc.CompanyServiceIfc;
import com.jbc.service.user.ifc.CustomerServiceIfc;
import com.jbc.util.modelUtil.CategoryUtil;

/**
 * {@code class} to create Data-bases in the system.
 */
public class DatabaseCreator {

	/**
	 * Creates a database with {@link com.jbc.model.user.Company},
	 * {@link com.jbc.model.user.Customer}, {@link com.jbc.model.Coupon}
	 * {@code Entity}s.
	 * 
	 * @throws UserEmailAlreadyExists
	 * 
	 * @see user#Company
	 * @see user#Customer
	 * @see model#Coupon
	 */
	public static void createDatabase(AdminServiceIfc adminServ, CompanyServiceIfc companyServ,
			CustomerServiceIfc customerServ) throws CompanyNotFoundException, UserEmailAlreadyExists {
		try {
			/* creating companies */
			Company[] companies = new Company[10];
			companies[0] = new Company("Nasa", "nasa@gmail.com", "1234");
			companies[1] = new Company("Basa", "basa@gmail.com", "1234");
			companies[2] = new Company("Hasa", "hasa@gmail.com", "1234");
			companies[3] = new Company("Tasa", "tasa@gmail.com", "1234");
			companies[4] = new Company("Lasa", "lasa@gmail.com", "1234");
			companies[5] = new Company("Intel", "intel@gmail.com", "1234");
			companies[6] = new Company("Toyota", "Toyota@gmail.com", "1234");
			companies[7] = new Company("AMD", "AMD@gmail.com", "1234");
			companies[8] = new Company("GMC", "gmc@gmail.com", "1234");
			companies[9] = new Company("Tesla", "tasla@gmail.com", "1234");

			/* adding companies */
			for (int i = 0; i < companies.length; i++) {
				adminServ.createCompany(0, companies[i]);
			}

			/* creating coupons */
			Coupon[] coupons = new Coupon[20];
			coupons[0] = new Coupon(CategoryUtil.ELECTRICITY, "TV", "Discount 50%", Date.valueOf("2010-01-01"),
					Date.valueOf("2024-01-01"), 20, 20325.7, "ELECTRICITY.image");
			coupons[0].setCompany(companies[0]);
			coupons[1] = new Coupon(CategoryUtil.FOOD, "Cake", "1+1", Date.valueOf("2020-01-01"),
					Date.valueOf("2023-01-01"), 100, 29.99, "FOOD.image");
			coupons[1].setCompany(companies[0]);
			coupons[2] = new Coupon(CategoryUtil.VACATION, "Resort free joiner", "+1 Person For Free",
					Date.valueOf("2020-02-08"), Date.valueOf("2022-02-21"), 0, 3000.0, "VACATION.image");
			coupons[2].setCompany(companies[1]);
			coupons[3] = new Coupon(CategoryUtil.SPORT, "Fitness GYM", "Equipment Rental", Date.valueOf("2020-01-03"),
					Date.valueOf("2023-01-03"), 20, 32.99, "SPORT.image");
			coupons[3].setCompany(companies[1]);
			coupons[4] = new Coupon(CategoryUtil.GAMING, "Gaming", "Computer Chair 50% OFF!",
					Date.valueOf("2020-10-11"), Date.valueOf("2023-10-11"), 7, 29.99, "GAMING.image");
			coupons[4].setCompany(companies[2]);
			coupons[5] = new Coupon(CategoryUtil.AUTOMOBILE, "CarX10ris", "Car 10% Off + Free Air Freshener",
					Date.valueOf("2007-02-10"), Date.valueOf("2022-02-10"), 3, 399.99, "AUTOMOBILE.image");
			coupons[5].setCompany(companies[2]);
			coupons[6] = new Coupon(CategoryUtil.ELECTRICITY, "Bulbs", "6 PEC + 6 PEC LED For Free",
					Date.valueOf("2021-01-12"), Date.valueOf("2024-01-12"), 6, 6.0, "ELECTRICITY.image");
			coupons[6].setCompany(companies[3]);
			coupons[7] = new Coupon(CategoryUtil.ELECTRICITY, "Radio", "Radio + Bluetooth Extension",
					Date.valueOf("2019-12-01"), Date.valueOf("2027-12-01"), 19, 19.19, "ELECTRICITY.image");
			coupons[7].setCompany(companies[3]);
			coupons[8] = new Coupon(CategoryUtil.RESTAURANT, "Michelin Voucher", "1 + 1 Guest For Free",
					Date.valueOf("2020-02-05"), Date.valueOf("2022-02-05"), 5, 49.99, "RESTAURANT.image");
			coupons[8].setCompany(companies[4]);
			coupons[9] = new Coupon(CategoryUtil.ATTRACTION, "Trip to france", "All included 3 days in Paris",
					Date.valueOf("2020-12-01"), Date.valueOf("2024-12-01"), 90, 499.99, "ATTRACTION.image");
			coupons[9].setCompany(companies[4]);
			coupons[10] = new Coupon(CategoryUtil.SPORT, "Macabi Football Game", "Free Ticket",
					Date.valueOf("2018-05-20"), Date.valueOf("2022-05-20"), 320, 0.00, "SPORT.image");
			coupons[10].setCompany(companies[5]);
			coupons[11] = new Coupon(CategoryUtil.GAMING, "Used PC", "90% Off Second Hand", Date.valueOf("2020-02-20"),
					Date.valueOf("2030-02-20"), 3, 120.99, "GAMING.image");
			coupons[11].setCompany(companies[5]);
			coupons[12] = new Coupon(CategoryUtil.FOOD, "Falafel Haderch", "+1 Free Falafel Ball",
					Date.valueOf("2020-03-23"), Date.valueOf("2050-03-23"), 99999, 0.01, "FOOD.image");
			coupons[12].setCompany(companies[6]);
			coupons[13] = new Coupon(CategoryUtil.VACATION, "Ski Trip In The Alphs", "Free Flight",
					Date.valueOf("2021-10-10"), Date.valueOf("2025-10-10"), 925, 99.99, "VACATION.image");
			coupons[13].setCompany(companies[6]);
			coupons[14] = new Coupon(CategoryUtil.RESTAURANT, "Shipodi Hatikva", "Extra Tahini",
					Date.valueOf("2015-09-08"), Date.valueOf("2025-10-09"), 1000, 0.1, "RESTAURANT.image");
			coupons[14].setCompany(companies[7]);
			coupons[15] = new Coupon(CategoryUtil.FOOD, "Rami Levi", "100 Shekels For Food In Porim",
					Date.valueOf("2020-02-25"), Date.valueOf("2025-02-26"), 99, 85.01, "FOOD.image");
			coupons[15].setCompany(companies[7]);
			coupons[16] = new Coupon(CategoryUtil.AUTOMOBILE, "Yosi's Garage", "10,000 Kilometers Treatment",
					Date.valueOf("2019-02-02"), Date.valueOf("2023-03-03"), 20, 582.57, "AUTOMOBILE.image");
			coupons[16].setCompany(companies[8]);
			coupons[17] = new Coupon(CategoryUtil.RESTAURANT, "Gordon Ramsay", "Free Meal With The Chef",
					Date.valueOf("2022-02-01"), Date.valueOf("2022-02-10"), 1, 9999.99, "RESTAURANT.image");
			coupons[17].setCompany(companies[8]);
			coupons[18] = new Coupon(CategoryUtil.ELECTRICITY, "New Hair Drier", "20% Discount",
					Date.valueOf("2020-08-01"), Date.valueOf("2023-07-02"), 50, 10.50, "ELECTRICITY.image");
			coupons[18].setCompany(companies[9]);
			coupons[19] = new Coupon(CategoryUtil.ATTRACTION, "Hermon Mountain Ski Day",
					"A Full Day Entry With Equipment", Date.valueOf("2019-01-20"), Date.valueOf("2024-01-20"), 100, 150,
					"ATTRACTION.image");
			coupons[19].setCompany(companies[9]);

			/* adding coupons */
			for (int i = 0; i < coupons.length; i++) {
				companyServ.addCoupon(coupons[i].getCompany().getId(), coupons[i]);
			}

			/* creating customers */
			Customer[] customers = new Customer[20];
			customers[0] = new Customer("David", "Levi", "levi@gmail.com", "4321");
			customers[1] = new Customer("Bavid", "Bevi", "bevi@gmail.com", "4321");
			customers[2] = new Customer("Loren", "Lao", "lao@gmail.com", "4321");
			customers[3] = new Customer("Cormilios", "Fudge", "fudge@gmail.com", "4321");
			customers[4] = new Customer("Supercajafaglistickejalifostec", "Super", "super@gmail.com", "4321");
			customers[5] = new Customer("BruceWayne", "Wayne", "wayne@gmail.com", "4321");
			customers[6] = new Customer("Batman", "Wayne", "batman@gmail.com", "4321");
			customers[7] = new Customer("Lil", "Big", "big@gmail.com", "4321");
			customers[8] = new Customer("Adam", "Sendler", "sendler@gmail.com", "4321");
			customers[9] = new Customer("George", "Clony", "clony@gmail.com", "4321");
			customers[10] = new Customer("Britney", "Blow", "blow@gmail.com", "4321");
			customers[11] = new Customer("Koari", "Noaki", "noaki@gmail.com", "4321");
			customers[12] = new Customer("Morgan", "Freeman", "freeman@gmail.com", "4321");
			customers[13] = new Customer("Django", "Jango", "jango@gmail.com", "4321");
			customers[14] = new Customer("Robi", "Gordon", "gordon@gmail.com", "4321");
			customers[15] = new Customer("Yoni", "Kaspi", "kaspi@gmail.com", "4321");
			customers[16] = new Customer("Ken", "Block", "block@gmail.com", "4321");
			customers[17] = new Customer("Travis", "Scott", "scott@gmail.com", "4321");
			customers[18] = new Customer("Asosicationable", "Asc", "asc@gmail.com", "4321");
			customers[19] = new Customer("Winni", "ThePoop", "poop@gmail.com", "4321");

			/* adding customers */
			for (int i = 0; i < customers.length; i++) {
				adminServ.addCustomer(0, customers[i]);
			}

			/* updating the previously added coupons and customers id */
			List<Coupon> couponList = adminServ.getCoupons();
			for (int i = 0; i < coupons.length; i++) {
				coupons[i] = couponList.get(i);
			}
			List<Customer> customerList = adminServ.getCustomers();
			for (int i = 0; i < customers.length; i++) {
				customers[i] = customerList.get(i);
			}

			/* adding coupons to customers */
			customers[0].getCoupons().add(coupons[19]);
			customers[0].getCoupons().add(coupons[18]);
			customers[1].getCoupons().add(coupons[16]);
			customers[1].getCoupons().add(coupons[17]);
			customers[2].getCoupons().add(coupons[12]);
			customers[2].getCoupons().add(coupons[5]);
			customers[3].getCoupons().add(coupons[1]);
			customers[3].getCoupons().add(coupons[9]);
			customers[4].getCoupons().add(coupons[8]);
			customers[4].getCoupons().add(coupons[0]);
			customers[5].getCoupons().add(coupons[4]);
			customers[5].getCoupons().add(coupons[6]);
			customers[6].getCoupons().add(coupons[12]);
			customers[6].getCoupons().add(coupons[16]);
			customers[7].getCoupons().add(coupons[11]);
			customers[7].getCoupons().add(coupons[5]);
			customers[8].getCoupons().add(coupons[17]);
			customers[8].getCoupons().add(coupons[2]);
			customers[9].getCoupons().add(coupons[5]);
			customers[9].getCoupons().add(coupons[3]);
			customers[10].getCoupons().add(coupons[4]);
			customers[10].getCoupons().add(coupons[10]);
			customers[11].getCoupons().add(coupons[7]);
			customers[11].getCoupons().add(coupons[16]);
			customers[12].getCoupons().add(coupons[18]);
			customers[12].getCoupons().add(coupons[13]);
			customers[13].getCoupons().add(coupons[6]);
			customers[13].getCoupons().add(coupons[0]);
			customers[14].getCoupons().add(coupons[2]);
			customers[14].getCoupons().add(coupons[4]);
			customers[15].getCoupons().add(coupons[10]);
			customers[15].getCoupons().add(coupons[13]);
			customers[16].getCoupons().add(coupons[12]);
			customers[16].getCoupons().add(coupons[9]);
			customers[17].getCoupons().add(coupons[14]);
			customers[17].getCoupons().add(coupons[18]);
			customers[18].getCoupons().add(coupons[19]);
			customers[18].getCoupons().add(coupons[15]);
			customers[19].getCoupons().add(coupons[11]);
			customers[19].getCoupons().add(coupons[16]);

			/* updating customers with the added coupons */
			/* doesn't work, because updateCustomer doesn't change his coupons status */
			for (int i = 0; i < customers.length; i++) {
				adminServ.updateCustomer(0, customers[i]);
			}
		} catch (AlreadyExistsException | CustomerDuplicateValueException | CustomerIsNullException
				| CustomerNullValueException | CustomerNotFoundException | CustomerIsEmptyException
				| CouponIsEmptyException | CouponNullValueException | CouponIsNullException
				| CouponDuplicateValueException | CompanyNullValueException | CompanyIsNullException
				| CompanyDuplicateValueException | CustomExceptionList e) {
			e.printStackTrace();
		}
	}

}
