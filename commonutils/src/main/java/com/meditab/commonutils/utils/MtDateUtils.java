package com.meditab.commonutils.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author
 * Mitesh Pithadiya
 * Meditab Software Inc.
 * miteshp@meditab.com
 * Created on 23/09/16 12:53 PM
 */
public class MtDateUtils {

    public static String convertTimeToFBDuration(long time) {
        StringBuilder strDuration = new StringBuilder();

        long now = new Date().getTime();

        long duration = now - time;

        Log.d("Minutes", String.valueOf(TimeUnit.MILLISECONDS.toMinutes(duration)));
        Log.d("Hours", String.valueOf(TimeUnit.MILLISECONDS.toHours(duration)));
        Log.d("Days", String.valueOf(TimeUnit.MILLISECONDS.toDays(duration)));

        if (TimeUnit.MILLISECONDS.toSeconds(duration) < 60) {
            // The difference is less than a minute.
            strDuration.append("Just Now");
        } else if (TimeUnit.MILLISECONDS.toMinutes(duration) < 60) {
            // The difference is less than an hour.
            long toMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

            if (toMinutes == 1) {
                strDuration.append("About a minute ago");
            } else {
                strDuration.append(toMinutes).append(" minutes ago");
            }
        } else if (TimeUnit.MILLISECONDS.toHours(duration) < 24) {
            // The difference is less than 24 hours.
            long toHours = TimeUnit.MILLISECONDS.toHours(duration);

            if (toHours == 1) {
                strDuration.append("About an hour ago ");
            } else {

                Calendar today = Calendar.getInstance();
                Calendar notificationCal = Calendar.getInstance();
                notificationCal.setTime(new Date(time));

                if (today.get(Calendar.DAY_OF_WEEK) != notificationCal.get(Calendar.DAY_OF_WEEK)) {
                    strDuration.append("Yesterday at ").append(DateFormatter
                            .convertMillisToStringDate(time, DateFormatter.HH_MM_A));
                } else {
                    strDuration.append(toHours).append(" hours ago");
                }
            }
        } else if (TimeUnit.MILLISECONDS.toDays(duration) < 7) {
            // THe difference is less than 7 days.
            long toDays = TimeUnit.MILLISECONDS.toDays(duration);

            if (toDays == 1) {
                strDuration.append("Yesterday at ").append(DateFormatter
                        .convertMillisToStringDate(time, DateFormatter.HH_MM_A));
            } else {
                strDuration.append(DateFormatter.convertMillisToStringDate(time,
                        DateFormatter.EEEE)).append(" at ").append(DateFormatter
                        .convertMillisToStringDate(time, DateFormatter.HH_MM_A));
            }
        } else {
            strDuration.append(DateFormatter.convertMillisToStringDate(time, DateFormatter.dd_MMM_YYYY_HH_MM_A));
        }
        return strDuration.toString();
    }

}
