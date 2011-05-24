package com.scandihealth.olympicsc.utilities.dates;

import org.jboss.seam.annotations.Name;

import java.util.Date;

@Name("dateUtilities")
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

    public boolean isDecember() {
        return new Date().getMonth() == 11;
    }

    public static void main(String[] args) {
        new DateUtilities().isEaster();
    }

    public boolean isEaster() {
        int year = new Date().getYear() + 1900;
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int easterMonth = (h + l - 7 * m + 114) / 31;
        int easterDay = (h + l - 7 * m + 114) % 31 + 1;
//        System.out.println("easter this year was: " + easterDay + "/" + easterMonth + " " + year);

        Date now = new Date();
        Date easter = new Date();
        easter.setMonth(easterMonth);
        easter.setDate(easterDay);

        // todo make this last more than 1 day :)
        return (now.getMonth() + 1) == easter.getMonth() && now.getDate() == easter.getDate();
    }
}
