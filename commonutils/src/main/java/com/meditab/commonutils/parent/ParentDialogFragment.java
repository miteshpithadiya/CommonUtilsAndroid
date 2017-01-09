package com.meditab.commonutils.parent;

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
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/7/16 6:36 PM
 */
public abstract class ParentDialogFragment<T> extends DialogFragment {
    protected ParentActivity activity;
    protected T fragment = (T) this;

    protected OnBackPressedFragmentListener backHandlerInterface;

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
//            onAttach(activity);
            this.activity = (ParentActivity) activity;
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
    protected ParentActivity getActivityInstance() {
        return activity;
    }

    public String getTitle() {
        return getString(R.string.app_name);
    }

    public boolean showHomeAsUpEnabled() {
        return false;
    }

    public boolean showAsDialog() {
        return false;
    }

    public boolean onBackPressHandled() {
        return false;
    }

}
