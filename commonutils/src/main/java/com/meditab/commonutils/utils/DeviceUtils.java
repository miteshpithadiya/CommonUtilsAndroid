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
public class DeviceUtils {

    Context mContext;

    public DeviceUtils(Context context) {
        mContext = context;
    }

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

    public String getOSVersion() {

        return Build.VERSION.RELEASE;

    }

    public String getAppVersion() {
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName()
                    , 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionName;
    }

    public String getMacAddress() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    public String getModelNo() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
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

    public float getWidth() {

        DisplayMetrics displayMetrics = getDeviceDisplay();

        return displayMetrics.widthPixels;
    }

    public float getHeight() {

        DisplayMetrics displayMetrics = getDeviceDisplay();

        return displayMetrics.heightPixels;
    }

    public float getHeightInDP() {

        DisplayMetrics displayMetrics = getDeviceDisplay();
        float density = mContext.getResources().getDisplayMetrics().density;

        return displayMetrics.heightPixels / density;
    }

    public float getWidthInDP() {

        DisplayMetrics displayMetrics = getDeviceDisplay();
        float density = mContext.getResources().getDisplayMetrics().density;

        return displayMetrics.widthPixels / density;
    }

    public float getDensity() {

        return mContext.getResources().getDisplayMetrics().density;
    }

    private DisplayMetrics getDeviceDisplay() {
        Display display = ((Activity) mContext).getWindowManager()
                .getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics;
    }

    public String getDeviceID() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public String getDeviceType() {
        return "A";
    }

    public String getDeviceName() {
        return NullUtils.getNotNullStringValue(Build.MANUFACTURER) + " - " + NullUtils.getNotNullStringValue(Build.MODEL);
    }
}
