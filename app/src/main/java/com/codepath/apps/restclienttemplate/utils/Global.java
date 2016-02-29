package com.codepath.apps.restclienttemplate.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by skammila on 2/28/16.
 */
public class Global {

    public static String getTimeSpan(String createdAt) {
        long timeMilli = getUnixTime(createdAt);
        long nowLngTime = System.currentTimeMillis();
        DateTime dateTime = new DateTime(timeMilli);
        DateTime now = new DateTime();
        long difference = Math.abs(timeMilli - nowLngTime);
        Period period = new Period(dateTime, now);
        PeriodFormatterBuilder formatterBuilder = new PeriodFormatterBuilder();
        if (difference > DateUtils.YEAR_IN_MILLIS) {
            formatterBuilder.appendYears().appendSuffix("y");
        } else if (difference > DateUtils.DAY_IN_MILLIS * 30) {
            formatterBuilder.appendMonths().appendSuffix("mo");
        } else if (difference > DateUtils.WEEK_IN_MILLIS) {
            formatterBuilder.appendWeeks().appendSuffix("w");
        } else if (difference > DateUtils.DAY_IN_MILLIS) {
            formatterBuilder.appendDays().appendSuffix("d");
        } else if (difference > DateUtils.HOUR_IN_MILLIS) {
            formatterBuilder.appendHours().appendSuffix("h");
        } else if (difference > DateUtils.MINUTE_IN_MILLIS) {
            formatterBuilder.appendMinutes().appendSuffix("mi");
        } else if (difference > DateUtils.SECOND_IN_MILLIS) {
            formatterBuilder.appendSeconds().appendSuffix("s");
        }
        String ends = formatterBuilder.printZeroNever().toFormatter().print(period);
//        String plural = ends.startsWith("1 ")?"":"s";
        return ends;
    }

    public static long getUnixTime(String createdAt) {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(createdAt );
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && isOnline();
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}
