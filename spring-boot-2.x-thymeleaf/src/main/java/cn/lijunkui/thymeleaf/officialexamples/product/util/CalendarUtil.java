package cn.lijunkui.thymeleaf.officialexamples.product.util;

import java.util.Calendar;

public class CalendarUtil {
	public static Calendar calendarFor(
            final int year,  final int month, final int day, final int hour, final int minute) {
        
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
        
    }
 
    private CalendarUtil() {
        super();
    }

}
