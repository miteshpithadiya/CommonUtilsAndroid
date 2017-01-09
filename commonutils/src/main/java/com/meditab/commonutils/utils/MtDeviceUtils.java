package com.meditab.commonutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by romac on 6/14/2016.
 */
public class MtDeviceUtils {

    /**
     * Created by Romac.
     * Method which returns boolean value according to the device type.
     *
     * @return :true if the device is tablet or else returns false in case of phone.
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getOSVersion() {

        return Build.VERSION.RELEASE;

    }

    public static String getAppVersion(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName()
                    , 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionName;
    }

    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    public static String getModelNo() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static float getWidth(Context context) {

        DisplayMetrics displayMetrics = getDeviceDisplay(context);

        return displayMetrics.widthPixels;
    }

    public static float getHeight(Context context) {

        DisplayMetrics displayMetrics = getDeviceDisplay(context);

        return displayMetrics.heightPixels;
    }

    public static float getHeightInDP(Context context) {

        DisplayMetrics displayMetrics = getDeviceDisplay(context);
        float density = context.getResources().getDisplayMetrics().density;

        return displayMetrics.heightPixels / density;
    }

    public static float getWidthInDP(Context context) {

        DisplayMetrics displayMetrics = getDeviceDisplay(context);
        float density = context.getResources().getDisplayMetrics().density;

        return displayMetrics.widthPixels / density;
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private static DisplayMetrics getDeviceDisplay(Context context) {
        Display display = ((Activity) context).getWindowManager()
                .getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics;
    }

    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceType() {
        return "A";
    }

    public static String getDeviceName() {
        return MtNullUtils.getNotNullStringValue(Build.MANUFACTURER) + " - " + MtNullUtils.getNotNullStringValue(Build.MODEL);
    }
}
