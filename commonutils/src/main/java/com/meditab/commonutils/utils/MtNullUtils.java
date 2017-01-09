package com.meditab.commonutils.utils;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 8/1/16 2:41 PM
 */
public class MtNullUtils {

    public static String getNotNullStringValue(String value) {
        return (value == null ? "" : value);
    }

    public static int getNotNullIntegerValue(String value) {

        int newInt;
        String newValue = null;
        try {
            newValue = (value == null) ? "0" : value;
            newInt = Integer.parseInt(newValue);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            newInt = 0;
        }
        return newInt;
    }

}
