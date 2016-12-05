package com.meditab.commonutils.firebaseutils.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.meditab.commonutils.firebaseutils.CuFCMContainer;
import com.meditab.commonutils.firebaseutils.FcmPrefManager;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:23 PM
 */
public class CUService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public CUService() {
        super("CUService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = FirebaseInstanceId.getInstance().getToken();
        try {
            Log.d("CUService", "token : " + token);
        } catch (Exception e) {
            Log.e("CUService", "token : " + e.getMessage());
        }
        FcmPrefManager.saveToken(this, token);
        FcmPrefManager.setTokenUpdated(this, true);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(CuFCMContainer.DEVICE_REGISTERED));
    }
}
