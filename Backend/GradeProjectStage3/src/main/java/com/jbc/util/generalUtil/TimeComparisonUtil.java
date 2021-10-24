package com.jbc.util.generalUtil;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code interface} containing {@code default} methods related to the
 * comparison time related objects.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see generalUtil#TimeZoneUtil
 */
public interface TimeComparisonUtil {

	/**
	 * <li>The time comparison is based on the {@link com.jbc.util.TimeZoneUtil}
	 * enum.</li>
	 * <p>
	 * 
	 * @param sqlDate
	 * @return {@code true} if the {@code endDate} is at the past. {@code false} if
	 *         the {@code endDate} is at the present or future.
	 */
	public default boolean isPast(Date sqlDate) {
		Date now = Date.valueOf(ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()))
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (now.after(sqlDate)) {
			return true;
		}
		return false;
	}

}
