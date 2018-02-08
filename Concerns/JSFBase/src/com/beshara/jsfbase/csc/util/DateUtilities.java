package com.beshara.jsfbase.csc.util;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class DateUtilities {
    public DateUtilities() {
    }

    public static Timestamp getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        return timestamp;
    }
}
