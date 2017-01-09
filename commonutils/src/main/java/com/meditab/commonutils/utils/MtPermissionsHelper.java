package com.meditab.commonutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meditab on 24/9/15.
 */
public class MtPermissionsHelper {


    private static final int PERMISSION = 100;
    PermissionCallback callback;
    Context mContext;
    HashMap<String, PermissionGrant> mapPermissionsGrants;

    public MtPermissionsHelper(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isPermissionGranted(String permission) {
        return mContext != null && permission != null && ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(String[] permissions, PermissionCallback permissionCallback) throws NullPointerException {
        this.callback = permissionCallback;
        mapPermissionsGrants = new HashMap<>();
        ArrayList<String> lstToBeRequestedPermissions = new ArrayList<>();


        for (String requestedPermission : permissions) {

            if (!isPermissionGranted(requestedPermission)) {
                lstToBeRequestedPermissions.add(requestedPermission);
                mapPermissionsGrants.put(requestedPermission, PermissionGrant
                        .DENIED);
            } else if (isPermissionGranted(requestedPermission)) {
                mapPermissionsGrants.put(requestedPermission, PermissionGrant.GRANTED);
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) mContext, requestedPermission)) {
                mapPermissionsGrants.put(requestedPermission, PermissionGrant
                        .NEVERSHOW);
            }

        }

        if (!lstToBeRequestedPermissions.isEmpty()) {
            if (mContext == null) {
                throw new NullPointerException("Activity instance cannot be null.");
            } else {
                ActivityCompat.requestPermissions((Activity) mContext,
                        lstToBeRequestedPermissions.toArray(new
                                String[lstToBeRequestedPermissions.size()]),
                        PERMISSION);
            }
        } else {
            if (permissionCallback != null) {
                permissionCallback.onResponseReceived(mapPermissionsGrants);
            }
        }

    }

    public void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
        int index = 0;
        for (String s : permissions) {
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                mapPermissionsGrants.put(s, PermissionGrant.GRANTED);
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) mContext, s)) {
                mapPermissionsGrants.put(s, PermissionGrant.NEVERSHOW);
            } else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                mapPermissionsGrants.put(s, PermissionGrant.DENIED);
            }

            index++;
        }

        if (callback != null) {
            callback.onResponseReceived(mapPermissionsGrants);
        }
    }

    public enum PermissionGrant {
        GRANTED, DENIED, NEVERSHOW;

    }

    public interface PermissionCallback {
        void onResponseReceived(HashMap<String, PermissionGrant> mapPermissionGrants);

    }
}
