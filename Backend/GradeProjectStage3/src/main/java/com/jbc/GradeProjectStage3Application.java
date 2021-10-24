package com.jbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jbc.system.Test;

/**
 * {@code class} that runs the {@code testAll} method in the {@link Test}
 * {@code Component}.
 * <p>
 * <li>Starts the <code>CouponExpirationDailyJob</code> at the start of the
 * system and stops it at the termination of the system.</li>
 * <p>
 * <li>Supported by Swagger Api, {@link com.jbc.swagger.HomeController} for more
 * information.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see system#Test
 * @see swagger#HomeController
 */
@SpringBootApplication
public class GradeProjectStage3Application {
	
	static boolean test=true;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GradeProjectStage3Application.class, args);
		context.getBean(Test.class).testAll(test);
	}

}
