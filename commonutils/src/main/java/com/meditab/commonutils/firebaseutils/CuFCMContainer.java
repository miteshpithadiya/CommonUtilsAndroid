package com.meditab.commonutils.firebaseutils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.messaging.RemoteMessage;
import com.meditab.commonutils.R;
import com.meditab.commonutils.firebaseutils.services.CUService;

import java.util.ArrayList;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:40 PM
 */
public class CuFCMContainer {

    public static final String DEVICE_REGISTERED = "DEVICE_REGISTERED";

    private static CuFCMContainer instance;

    private ArrayList<CUFCMListener> callbacksList = new ArrayList<>();
    private Context mContext;

    public CuFCMContainer(Context context) {
        this.mContext = context;
        initialize();
    }

    private void initialize() {
        LocalBroadcastManager.getInstance(mContext).registerReceiver(broadcastReceiver, new IntentFilter(DEVICE_REGISTERED));

        /**
         * check if device has Google play service on it
         */
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int status = api.isGooglePlayServicesAvailable(mContext);

        /**
         * If true, process the {@link SaveFCMIdService}
         */
        if (status == ConnectionResult.SUCCESS) {
            mContext.startService(new Intent(mContext, CUService.class));
        } else {
            for (CUFCMListener callback : callbacksList) {
                callback.onPlayServiceError();
            }
        }
    }

    public static CuFCMContainer getInstance(Context context) {
        if (instance == null) {
            instance = new CuFCMContainer(context);
        }
        return instance;
    }


    public void registerListener(CUFCMListener callback) {
        callbacksList.add(callback);
    }

    public void unregisterListener(CUFCMListener callback) {
        callbacksList.remove(callback);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String token = FcmPrefManager.getToken(context);
            if (!TextUtils.isEmpty(token)) {
                notifyTokenRefreshed(token);
            }
        }
    };

    private void notifyTokenRefreshed(String token) {
        for (CUFCMListener callback : callbacksList) {
            callback.onDeviceRegistered(token);
        }
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        for (CUFCMListener callback : callbacksList) {
            callback.onMessageReceived(remoteMessage);
        }

        try {
            Log.e("onMessageReceived", "Body : " + remoteMessage.getNotification().getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.e("onMessageReceived", "Title : " + remoteMessage.getNotification().getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.e("onMessageReceived", "Data : " + remoteMessage.getData().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

       // sendNotification(remoteMessage.getNotification().getBody());
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
//        // Intent intent = new Intent(this, .class);
//        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0 /* Request code */, null,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
//                .setSmallIcon(R.mipmap.app_icon)
//                .setContentTitle("FCM Message")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
