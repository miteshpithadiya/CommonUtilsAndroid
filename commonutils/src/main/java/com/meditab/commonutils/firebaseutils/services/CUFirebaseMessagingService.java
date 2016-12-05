package com.meditab.commonutils.firebaseutils.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.meditab.commonutils.firebaseutils.CuFCMContainer;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:12 PM
 */
public class CUFirebaseMessagingService  extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        try {
//            Log.e("onMessageReceived", "Body : " + remoteMessage.getNotification().getBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.e("onMessageReceived", "Title : " + remoteMessage.getNotification().getTitle());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.e("onMessageReceived", "Data : " + remoteMessage.getData().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CuFCMContainer.getInstance(this).onMessageReceived(remoteMessage);
    }
}
