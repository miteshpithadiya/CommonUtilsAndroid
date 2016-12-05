package com.meditab.commonutils.base;

import android.app.Application;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 8/15/16 5:29 PM
 */
public class BaseApplication {

    private static boolean isTablet;

    public static boolean isTablet() {
        return isTablet;
    }

    public static void setIsTablet(boolean isTablet) {
        BaseApplication.isTablet = isTablet;
    }
}
