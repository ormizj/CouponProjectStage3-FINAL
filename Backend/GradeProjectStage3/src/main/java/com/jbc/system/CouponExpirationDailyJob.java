package com.jbc.system;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbc.model.Coupon;
import com.jbc.model.Logger;
import com.jbc.repository.CouponRepository;
import com.jbc.repository.LoggerRepository;
import com.jbc.util.generalUtil.PathUtil;
import com.jbc.util.generalUtil.StringClass;
import com.jbc.util.generalUtil.TimeComparisonUtil;
import com.jbc.util.generalUtil.TimeZoneUtil;
import com.jbc.util.modelUtil.ModelEntityUtil;
import com.jbc.util.serviceUtil.CouponHelper;
import com.jbc.util.serviceUtil.ModelActionUtil;
import com.jbc.util.serviceUtil.loggerUtil.CouponLoggerAttributer;
import com.jbc.util.systemUtil.TerminationWaitTime;

/**
 * {@code Component} that manages the daily job of the system, deleting expired
 * {@link com.jbc.model.Coupon} {@code Entity}s based on their {@code endDate}
 * at 12AM.
 * <p>
 * <li>Timezone is based off the {@link com.jbc.util.generalUtil.TimeZoneUtil}
 * {@code enum}.</li>
 * <p>
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalUtil#TimeZoneUtil
 * @see model#Coupon
 */
@Component
public final class CouponExpirationDailyJob implements Runnable, TimeComparisonUtil, CouponHelper, InitializingBean,
		DisposableBean, CouponLoggerAttributer {

	/* attributes */
	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private LoggerRepository loggerRepo;
	private boolean running;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	/**
	 * Starts the {@link com.jbc.model.Coupon} thread, if its not already
	 * {@code running}, the thread will operate at 12AM based on the
	 * {@link com.jbc.util.generalUtil.TimeZoneUtil} area and will repeat the
	 * operation every 24 hours.
	 * 
	 * @return {@code true} if the thread isn't already {@code running} and the
	 *         initilization was successful. {@code false} if the thread is already
	 *         {@code running}.
	 * @see #run()
	 * @see #running
	 * @see #scheduler
	 */
	public boolean start() {
		if (!running) {
			running = true;
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()));
			ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
			/* if "nextRun" is before "now" add 1 day to "nextRun" */
			if (now.compareTo(nextRun) > 0)
				nextRun = nextRun.plusDays(1);
			Duration duration = Duration.between(now, nextRun);
			scheduler.scheduleAtFixedRate(this, duration.getSeconds(), TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
			return true;
		}
		return false;
	}

	/**
	 * Shuts down the {@code scheduler} thread, tried up to
	 * {@link com.jbc.util.systemUtil.TerminationWaitTime} {@code MINUTES} value to
	 * terminate the {@code scheduler} thread.
	 * 
	 * @return {@code true} if the {@code CouponExpirationDailyJob} is not
	 *         {@code running} anymore. {@code false} if the
	 *         {@code CouponExpirationDailyJob} thread shut down was unsuccessful is
	 *         still {@code running}.
	 * @see util#systemUtil#TerminationWaitTime
	 * @see #scheduler
	 * @see #running
	 */
	public boolean stop() {
		if (running) {
			try {
				scheduler.shutdown();
				if (!scheduler.awaitTermination(TerminationWaitTime.TIME.toInt(), TimeUnit.MINUTES)) {
					return false;
				}
				running = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * Deletes every {@link com.jbc.model.Coupon} {@code Entity} from the system
	 * data-base that is expired, based on their {@code endDate} value.
	 * 
	 * @see util#generalUtil#StringClass
	 * @see repository#CouponRepository
	 */
	@Override
	public void run() {
		List<Coupon> coupons = couponRepo.findAll();
		coupons.forEach(coupon -> {
			synchronized (StringClass.COUPON_ID_SYNC + coupon.getId()) {
				Optional<Coupon> couponOption = couponRepo.findById(coupon.getId());
				if (couponOption.isPresent() && isPast(coupon.getEndDate())) {
					Coupon removedCoupon = couponOption.get();
					loggerRepo.save(new Logger(0, null, removedCoupon.getId(), ModelEntityUtil.COUPON,
							ModelActionUtil.DELETE, attributeCoupon(removedCoupon), null));
					deleteCoupon(removedCoupon, couponRepo);
				}
			}
		});
	}

	/**
	 * Starts the {@link com.jbc.system.CouponExpirationDailyJob} thread with server
	 * startup.
	 * 
	 * @see util#SystemUtil#CouponExpirationDailyJobUtil
	 * @see #getDate
	 * @see #start()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		String classPath = PathUtil.COUPON_EXPIRATION_DAILY_JOB_PATH.toString();
		String className = PathUtil.COUPON_EXPIRATION_DAILY_JOB_NAME.toString();
		System.out.println(getDate() + "  " + classPath + " : Starting the " + className + " thread...");
		if (start()) {
			System.out.println(getDate() + "  " + classPath + " : " + className + " thread is running.");
			return;
		}
		System.err.println(getDate() + "  " + classPath + " : " + className
				+ " thread has encountered an error and is not running.");
	}

	/**
	 * Starts the {@link com.jbc.system.CouponExpirationDailyJob} thread with server
	 * shutdown.
	 * 
	 * @see util#SystemUtil#CouponExpirationDailyJobUtil
	 * @see #getDate
	 * @see #stop()
	 */
	@Override
	public void destroy() throws Exception {
		String classPath = PathUtil.COUPON_EXPIRATION_DAILY_JOB_PATH.toString();
		String className = PathUtil.COUPON_EXPIRATION_DAILY_JOB_NAME.toString();
		System.out.println(getDate() + "  " + classPath + " : Closing the " + className + " thread...");
		if (stop()) {
			System.out.println(getDate() + "  " + classPath + " : " + className + " thread is closed.");
			return;
		}
		System.err.println(getDate() + "  " + classPath + " : " + className
				+ " thread has encountered an error and is not closed.");
	}

	/**
	 * Helper method, to get the current date, the length of the {@code Date} length
	 * will always be 12.
	 * 
	 * @see util#systemUtil#TimeZoneUtil
	 */
	private String getDate() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()));
		String time = now.format(DateTimeFormatter.ISO_LOCAL_TIME);
		while (time.length() < 12)
			time += 0;
		String date = now.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + time;
		return date;
	};

}