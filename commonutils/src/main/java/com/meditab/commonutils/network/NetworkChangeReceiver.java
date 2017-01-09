package com.meditab.commonutils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.meditab.commonutils.utils.MtNetworkUtils;

/**
 * Created by romac on 6/11/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityHolder.getInstance().notifyAll(MtNetworkUtils
                .isNetworkAvailable(context));
    }
}
