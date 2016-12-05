package com.meditab.commonutils.firebaseutils;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 9/15/16 1:43 PM
 */
public interface CUFCMListener {
    /**
     * Called when device is registered to FCM servers and received token
     *
     * @param deviceToken
     */
    void onDeviceRegistered(String deviceToken);

    /**
     * Called when downstream message receive by device.
     *
     * @param remoteMessage
     */
    void onMessageReceived(RemoteMessage remoteMessage);

    /**
     * Called when device is unable to google play service.
     */
    void onPlayServiceError();
}
