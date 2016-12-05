package com.meditab.commonutils.firebaseutils;

import android.content.Context;

import com.meditab.commonutils.utils.SharedPrefUtils;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:32 PM
 */
public class FcmPrefManager {

    public static final String KEY_FCM_TOKEN = "fcm_token";
    public static final String KEY_FCM_TOKEN_UPDATED = "fcm_token_updated";

    public static void saveToken(Context context, String token) {
        SharedPrefUtils prefUtils = new SharedPrefUtils(context);
        prefUtils.setValue(KEY_FCM_TOKEN, token, SharedPrefUtils.PREF_FILE_FIREBASE);
    }

    public static String getToken(Context context) {
        SharedPrefUtils prefUtils = new SharedPrefUtils(context);
        String token = prefUtils.getValue(KEY_FCM_TOKEN, "", SharedPrefUtils.PREF_FILE_FIREBASE);
        return token;
    }

    public static void setTokenUpdated(Context context, boolean isTokenUpdated) {
        SharedPrefUtils prefUtils = new SharedPrefUtils(context);
        prefUtils.setValue(KEY_FCM_TOKEN_UPDATED, isTokenUpdated, SharedPrefUtils.PREF_FILE_FIREBASE);
    }

    public static boolean isTokenUpdated(Context context) {
        SharedPrefUtils prefUtils = new SharedPrefUtils(context);
        boolean flag = prefUtils.getValue(KEY_FCM_TOKEN, false, SharedPrefUtils.PREF_FILE_FIREBASE);
        return flag;
    }


}
