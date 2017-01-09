/*
 * Copyright (c) 2015.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by meditab on 26/11/15.
 */
public final class MtPhoneUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    private MtPhoneUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * Call System texting interface
     *
     * @param activity Activity
     *                 param PhoneNumber phone number
     *                 param SmsContent message content
     */
    public static void sendMessage(Context activity, String phoneNumber,
                                   String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);

    }

    /**
     * Call the system call interface
     *
     * @param context
     *                param PhoneNumber phone number
     */
    public static void callPhones(Context context, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 1) {
            return;
        }
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}

