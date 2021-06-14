package com.sparta.adrian.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.sparta.adrian.view.Logging;

public class DateFormat {
    public static java.sql.Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        java.sql.Date sqlDate = null;
        try {
            Date tempDate = formatter.parse(date);
            sqlDate = new java.sql.Date(tempDate.getTime());
        } catch (ParseException e) {
            Logging.errorLog("Date not parsed");
            e.printStackTrace();
        }
        return sqlDate;
    }

    public static String formatDate(java.sql.Date sqlDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("E dd MMM yyyy", Locale.ENGLISH);
        return formatter.format(sqlDate);
    }
}
