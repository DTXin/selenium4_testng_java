package org.example.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePicker {

    public int countMonths(String month, String year) {
        int monthCount = 0;

        try {
            Date date = new SimpleDateFormat("MMMM yyyy").parse(month + " " + year);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            System.out.println("Date: " + date);
            System.out.println("Month: " + cal.get(Calendar.MONTH) + 1);
            System.out.println("Year: " + cal.get(Calendar.YEAR));
            System.out.println("Day: " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println("Day of week: " + cal.get(Calendar.DAY_OF_WEEK));
            System.out.println("Day of week in month: " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
            System.out.println("Week of year: " + cal.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Week of month: " + cal.get(Calendar.WEEK_OF_MONTH));
            System.out.println("CAL:" + cal.getTime());

            monthCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println("Number of days in " + month + " " + year + ": " + monthCount);
        } catch (Exception e) {
            System.out.println("Invalid month or year format: " + e.getMessage());
        }

        return monthCount;
    }

    public void getDate(int day) {
        Calendar date = Calendar.getInstance();
        System.out.println("Calendar.DATE: " + Calendar.DATE);
        date.add(Calendar.DATE, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        System.out.println(simpleDateFormat.format(date.getTime()));
    }
}
