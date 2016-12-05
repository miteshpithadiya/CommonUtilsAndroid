package com.meditab.commonutils.utils;

import android.text.format.DateUtils;

import com.meditab.commonutils.DefaultMessages;
import com.meditab.commonutils.exceptions.InvalidDateFormatException;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by romac on 6/10/2016.
 */
public class DateComparator {

    /**
     * @param strDate1  The first date from which is to be compared
     * @param strDate2  The second date from which is to be compared
     * @param strFormat The format in which the dates are passed
     * @return Greater than zero if strDate1 is after strDate2
     * Less than zero if strDate1 is before strDate2
     * Zero if both the dates are equal
     */
    public static int compareDates(String strDate1, String strDate2, String strFormat) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.getDefault());
            Date date1 = sdf.parse(strDate1);
            Date date2 = sdf.parse(strDate2);

            System.out.println(sdf.format(date1));
            System.out.println(sdf.format(date2));
            return date1.compareTo(date2);

        } catch (ParseException ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public static boolean isToday(String strDate, String strFormat) throws InvalidDateFormatException {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.getDefault());
        Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidDateFormatException(String.format
                    (DefaultMessages.INVALID_DATE_FORMAT, strFormat, strDate));
        }
        return DateUtils.isToday(date.getTime());

    }

    public static boolean isToday(@NotNull Date date) {

        return DateUtils.isToday(date.getTime());

    }


    public static boolean isPastDate(@NotNull String strDate, @NotNull String
            strFormat) throws InvalidDateFormatException {


        try {
            return new SimpleDateFormat(strFormat, Locale.getDefault()).parse(strDate).before
                    (new Date());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidDateFormatException(String.format
                    (DefaultMessages.INVALID_DATE_FORMAT, strFormat, strDate));
        }

    }


    public static boolean isPastDate(@NotNull Date date) {


        Date todayDate = new Date();
        return date.before(todayDate);


    }

    public static boolean isFutureDate(@NotNull Date date) {


        Date todayDate = new Date();
        return date.after(todayDate);


    }

    public static boolean isFutureDate(@NotNull String strDate, @NotNull String
            strFormat) throws InvalidDateFormatException {
        try {
            return new SimpleDateFormat(strFormat, Locale.getDefault()).parse
                    (strDate).after(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidDateFormatException(String.format
                    (DefaultMessages.INVALID_DATE_FORMAT, strFormat, strDate));
        }

    }

    public static boolean isDateLesser(@NotNull String strDate1, @NotNull
    String strDate2, @NotNull String strFormat) throws InvalidDateFormatException {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.getDefault());
            Date date1 = sdf.parse(strDate1);
            Date date2 = sdf.parse(strDate2);
            return date1.after(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidDateFormatException(String.format
                    (DefaultMessages.INVALID_DATE_FORMAT, strFormat, strDate1));
        }


    }


    public static boolean isDateGreater(@NotNull String strDate1, @NotNull
    String strDate2, @NotNull String strFormat) throws InvalidDateFormatException {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.getDefault());
            Date date1 = sdf.parse(strDate1);
            Date date2 = sdf.parse(strDate2);
            return date1.before(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new InvalidDateFormatException(String.format
                    (DefaultMessages.INVALID_DATE_FORMAT, strFormat, strDate1));
        }


    }

    public static boolean isTimeGreater(long millis1, long millis2) {


        return millis1 < millis2;


    }

    public static boolean isTimeLesser(long millis1, long millis2) {


        return millis1 > millis2;


    }
}
