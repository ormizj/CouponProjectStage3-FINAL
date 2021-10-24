package scrap.scrap.tests;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.jbc.util.generalUtil.TimeZoneUtil;

public class CompareTime {

	public static boolean isPast(Date endDate) {
		Date now = Date.valueOf(ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()))
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (now.after(endDate)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		if (CompareTime.isPast(Date.valueOf("2021-07-11")))
			System.out.println("in past");
		;
	}

}
