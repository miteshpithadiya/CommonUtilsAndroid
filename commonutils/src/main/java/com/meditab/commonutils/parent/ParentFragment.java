package com.meditab.commonutils.parent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.meditab.commonutils.R;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/7/16 6:36 PM
 */
public abstract class ParentFragment<T> extends Fragment {


    protected ParentActivity activity;
    protected T fragment = (T) this;

    public boolean onBackPressHandled() {
        return false;
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ParentActivity) getActivity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.activity = (ParentActivity) activity;
        }
    }

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
    protected ParentActivity getActivityInstance() {
        return activity;
    }

    protected String getTitle() {
        return getString(R.string.app_name);
    }

    protected boolean showHomeAsUpEnabled() {
        return false;
    }

    protected void setupFragmentComponent() {
    }

    ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent();
    }
}
