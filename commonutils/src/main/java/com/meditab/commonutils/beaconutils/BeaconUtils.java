package com.meditab.commonutils.beaconutils;

import android.content.Context;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by niravtukadiya on 08/10/16.
 */

public class BeaconUtils {

    private static BeaconUtils instance;
    private ArrayList<MonitoringListener> monitoringListeners = new ArrayList<>();
    private BeaconManager beaconManager;
    private Context mContext;

    public BeaconUtils(Context context) {
        this.mContext = context;
        init();
    }

    public static BeaconUtils getInstance(Context context) {
        if (instance == null) {
            instance = new BeaconUtils(context);
        }

        return instance;
    }

    public void init() {

        beaconManager = new BeaconManager(mContext);

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {

                if (!list.isEmpty()) {
                    for (Beacon b : list) {
                        Log.d("Beacon", "Found: " + b.getMinor());
                    }

                    for (MonitoringListener callback : monitoringListeners) {
                        callback.onEnteredRegion(region, list);
                    }
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                for (MonitoringListener callback : monitoringListeners) {
                    callback.onExitedRegion(region);
                }
            }
        });

        beaconManager.setBackgroundScanPeriod(10000, 5000);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                ArrayList<Region> regionsToMonitor = getRegionsToMonitor();
                for (Region region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });

    }

    private ArrayList<Region> getRegionsToMonitor() {

        ArrayList<Region> regionsToMonitor = new ArrayList<>();

//        final Region room1 = new Region("room1", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 501);
//        regionsToMonitor.add(room1);
//
//        final Region room2 = new Region("room2", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 502);
//        regionsToMonitor.add(room2);
//
//        final Region room3 = new Region("room3", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 503);
//        regionsToMonitor.add(room3);
//
//        final Region room4 = new Region("room4", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 504);
//        regionsToMonitor.add(room4);
//
//        final Region room5 = new Region("room5", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 505);
//        regionsToMonitor.add(room5);

        final Region room1 = new Region("clinic1", UUID.fromString("5E0C63DE-05F0-1F1B-BF2D-BC3936CEA4C0"), 6000, 605);
        regionsToMonitor.add(room1);

        final Region room2 = new Region("clinic2", UUID.fromString("91007AF9-FCEE-5340-1D09-C3097E35D65C"), 5000, 503);
        regionsToMonitor.add(room2);

        return regionsToMonitor;
    }

    public void registerMonitoringListener(MonitoringListener listener) {
        monitoringListeners.add(listener);
    }

    public void unregisterMonitoringListener(MonitoringListener listener) {
        monitoringListeners.remove(listener);
    }

    public interface MonitoringListener {
        void onEnteredRegion(Region var1, List<Beacon> var2);

        void onExitedRegion(Region var1);
    }

}
