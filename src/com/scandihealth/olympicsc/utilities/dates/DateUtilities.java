package com.scandihealth.olympicsc.utilities.dates;

import java.util.Date;

public class DateUtilities {
    public static boolean isBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    public static boolean isAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    public static int dayDifference(Date date1, Date date2) {
        long date1Time = date1.getTime();
        long date2Time = date2.getTime();
        long difference = date2Time - date1Time;

        return (int) difference / (1000 * 60 * 60 * 24);
    }
}
