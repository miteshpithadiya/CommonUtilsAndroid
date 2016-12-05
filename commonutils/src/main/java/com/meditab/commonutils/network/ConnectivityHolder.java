package com.meditab.commonutils.network;

import java.util.ArrayList;

/**
 * Created by romac on 6/11/2016.
 */
public class ConnectivityHolder {

    private static ConnectivityHolder ourInstance = new ConnectivityHolder();
    private ArrayList<OnConnectivityChangeListener>
            list = new ArrayList<>();

    private ConnectivityHolder() {
    }

    public static ConnectivityHolder getInstance() {
        return ourInstance;
    }

    public void add(OnConnectivityChangeListener onConnectivityChangeListener) {
        if (onConnectivityChangeListener != null && !contains
                (onConnectivityChangeListener)) {
            list.add(onConnectivityChangeListener);
        }
    }

    public void remove(OnConnectivityChangeListener
                               onConnectivityChangeListener) {
        if (onConnectivityChangeListener != null) {
            list.remove(onConnectivityChangeListener);
        }
    }

    public boolean contains(OnConnectivityChangeListener
                                    onConnectivityChangeListener) {
        return (onConnectivityChangeListener != null && list.contains
                (onConnectivityChangeListener));

    }

    public void notifyAll(boolean isConnectionAvailable){
        for (OnConnectivityChangeListener onConnectivityChangeListener :
                list) {
            onConnectivityChangeListener.onConnectivityChanged(isConnectionAvailable);
        }
    }
}
