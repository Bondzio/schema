package com.scandihealth.olympicsc.utilities.dates;

import java.util.Date;

public class DateUtilities {

    public static int dayDifference(Date date1, Date date2) {
        long date1Time = date1.getTime();
        long date2Time = date2.getTime();
        long difference = date2Time - date1Time;

        return (int) difference / (1000 * 60 * 60 * 24);
    }
}
