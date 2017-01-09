package com.meditab.commonutils.utils;

/**
 * Created by romac on 6/6/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.meditab.commonutils.R;

import org.jetbrains.annotations.NotNull;

/**
 * Snackbars
 *
 * @author NiravT
 */
public class MtSnackBarUtils {

    private MtSnackBarUtils() {
    }


    /**
     * @param activity: calling bActivity
     * @param message:  message inside snackbar
     */
    public static Snackbar getSnackbar(Context mContext, @NotNull Activity activity, final String message) {
        final View content = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (content == null)
            return null;
        return createSnackBar(mContext, message, null, null, content, Snackbar.LENGTH_SHORT);

    }

    /**
     * @param activity: calling bActivity
     * @param message:  message inside snackbar
     * @param length:   determine for how long the snackbar will be shown
     */
    public static Snackbar getSnackbar(Context mContext, @NotNull Activity activity, final String message, int
            length) {
        final View content = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (content == null)
            return null;
        return createSnackBar(mContext, message, null, null, content, length);

    }


    /**
     * @param activity:      calling bActivity
     * @param message:       message inside snackbar
     * @param action:        action inside snackbar
     * @param clickListener: click listener for action item inside snackbar
     */
    public static Snackbar getSnackbar(Context mContext, @NotNull Activity activity, final String message, String
            action, View
                                               .OnClickListener clickListener) {
        final View content = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (content == null)
            return null;
        return createSnackBar(mContext, message, action, clickListener, content, Snackbar.LENGTH_SHORT);
    }

    /**
     * @param activity:      calling bActivity
     * @param message:       message inside snackbar
     * @param action:        action inside snackbar
     * @param clickListener: click listener for action item inside snackbar
     */
    public static Snackbar getSnackbar(Context mContext, @NotNull Activity activity, final String message, String
            action, View
                                               .OnClickListener
                                               clickListener, int length) {
        final View content = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (content == null)
            return null;
        return createSnackBar(mContext, message, action, clickListener, content, length);
    }


    /**
     * @param view:          View below which snackbar is to be shown
     * @param msg:           message inside snackbar
     * @param action:        action inside snackbar
     * @param clickListener: click listener for action item inside snackbar
     * @param length:        determines for how long the snackbar will be visible
     */
    public static Snackbar getSnackbar(Context mContext, View view, String msg, String action, View.OnClickListener
            clickListener, int length) {
        return createSnackBar(mContext, msg, action, clickListener, view, length);
    }

    /**
     * @param content:       view where snackbar will be shown
     * @param length:        determines for how long the snackbar will be visible
     * @param message:       message inside snackbar
     * @param action:        action inside snackbar
     * @param clickListener: click listener for action item inside snackbar
     */
    private static Snackbar createSnackBar(Context mContext, String message, String action, View.OnClickListener
            clickListener, View content, int length) {
        Snackbar snackbar = Snackbar.make(content, message, length);
        if (action != null) {
            snackbar.setAction(action, clickListener);
            if (mContext != null) {
                snackbar.setActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }
        }

        try {
            View view = snackbar.getView();
            TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            snackbar.setActionTextColor(Color.parseColor("#F39E55"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return snackbar;
    }

//    public void setTextColor(Snackbar snackbar, int color) {
//        View view = snackbar.getView();
//        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setTextColor(color);
//    }

}
