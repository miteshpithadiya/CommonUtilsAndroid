package com.meditab.commonutils.firebaseutils.services;

import android.app.IntentService;
import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:22 PM
 */
public class CUFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, CUService.class);
        startService(intent);
    }
}
