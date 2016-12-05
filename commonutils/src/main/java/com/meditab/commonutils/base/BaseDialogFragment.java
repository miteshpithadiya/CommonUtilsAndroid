/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meditab.commonutils.R;
import com.meditab.commonutils.callbacks.OnBackPressedFragmentListener;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 8/6/16 7:47 PM.
 */

public abstract class BaseDialogFragment<T> extends DialogFragment {

    protected BaseActivity activity;
    protected T fragment = (T) this;

    protected OnBackPressedFragmentListener backHandlerInterface;

    public boolean onBackPressHandled() {
        return false;
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) getActivity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            onAttach(activity);
            this.activity = (BaseActivity) activity;
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        if (getShowsDialog()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            return onCreateViewAbstract(inflater, container, savedInstanceState);
        }
    }

    public abstract View onCreateViewAbstract(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState);

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    /**
     * Get Instance of current bActivity
     *
     * @return bActivity instance of Sub Activity
     */
    protected BaseActivity getActivityInstance() {
        return activity;
    }


    public String getTitle() {
        return getString(R.string.app_name);
    }

    public boolean showHomeAsUpEnabled() {
        return false;
    }

    public boolean showAsDialog() {
        return BaseApplication.isTablet();
    }

}
