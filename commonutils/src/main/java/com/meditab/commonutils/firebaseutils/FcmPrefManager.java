package com.meditab.commonutils.firebaseutils;

import android.content.Context;

import com.meditab.commonutils.utils.MtSharedPrefUtils;

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

    public static final String PREF_FILE_FIREBASE = "firebase";

    public static void saveToken(Context context, String token) {
        MtSharedPrefUtils.setValue(context, KEY_FCM_TOKEN, token, PREF_FILE_FIREBASE);
    }

    public static String getToken(Context context) {
        String token = MtSharedPrefUtils.getValue(context, KEY_FCM_TOKEN, "", PREF_FILE_FIREBASE);
        return token;
    }

    public static void setTokenUpdated(Context context, boolean isTokenUpdated) {
        MtSharedPrefUtils.setValue(context, KEY_FCM_TOKEN_UPDATED, isTokenUpdated, PREF_FILE_FIREBASE);
    }

    public static boolean isTokenUpdated(Context context) {
        boolean flag = MtSharedPrefUtils.getValue(context, KEY_FCM_TOKEN, false, PREF_FILE_FIREBASE);
        return flag;
    }


}
