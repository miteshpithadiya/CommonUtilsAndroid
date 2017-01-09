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
import android.support.v4.app.Fragment;

import com.meditab.commonutils.R;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p/>
 *         Created on 8/6/16 7:47 PM.
 */

public class BaseFragment<T> extends Fragment {

    protected BaseActivity activity;
    protected T fragment = (T) this;

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
            this.activity = (BaseActivity) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (activity.getSupportFragmentManager().getFragments().contains(fragment)) {
//            setHasOptionsMenu(true);
//            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                activity.onBackPressed();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
}
