/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p/>
 *         Created on 24/5/16 7:37 PM.
 */
public class Common {

    public static boolean isEmptyWithTrim(String text) {
        return (TextUtils.isEmpty(text) || TextUtils.isEmpty(text.trim()));
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
