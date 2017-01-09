/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.parent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;

import com.meditab.commonutils.R;
import com.meditab.commonutils.callbacks.FragmentLoader;
import com.meditab.commonutils.callbacks.OnBackPressedListener;
import com.meditab.commonutils.parent.ParentDialogFragment;

import java.util.Stack;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p/>
 *         Created on 8/6/16 12:48 PM.
 */

public class ParentActivity<T> extends AppCompatActivity implements FragmentLoader {

    protected T bActivity = (T) this;
    protected Context bContext = this;
    private ProgressDialog mProgressDialog;
    private Stack<Fragment> fragmentStack = new Stack<>();
    private boolean onCreateCalled = false;

    protected boolean bIsTablet;

    /**
     * Get Instance of current bActivity
     *
     * @return bActivity instance of Sub Activity
     */
    protected T getActivityInstance() {
        return bActivity;
    }

    /**
     * Get bContext of current bActivity
     *
     * @return
     */
    protected Context getContext() {
        return bContext;
    }

    public void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(this, "", msg);
    }

    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * For hiding the softkeyboard.
     *
     * @param view The view.
     */
    public void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void loadFragment(int containerId, Fragment fragment, boolean addToStack, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        if (addToStack) {
            fragmentStack.push(fragment);
            //transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        //TODO commit and executePendingTransactions methods should be replaced by commitNow method once it is available in support library
        transaction.commit();
        // getSupportFragmentManager().executePendingTransactions();

    }

    @Override
    public void loadFragment(int containerId, Fragment fragment, boolean addToStack) {
        loadFragment(containerId, fragment, addToStack, fragment.getClass().getSimpleName());
    }

    @Override
    public void loadFragment(int containerId, Fragment fragment, String tag) {
        loadFragment(containerId, fragment, false, tag);
    }

    @Override
    public void loadFragment(int containerId, Fragment fragment) {
        loadFragment(containerId, fragment, false, fragment.getClass().getSimpleName());
    }

    @Override
    public void loadDialogFragment(int containerId, DialogFragment fragment, boolean addToStack, String tag) {

        if (fragment instanceof ParentDialogFragment && ((ParentDialogFragment) fragment).showAsDialog()) {
            if (addToStack) {
                fragmentStack.push(fragment);
            }
            fragment.show(getSupportFragmentManager(), tag);
        } else if (containerId != 0) {
            loadFragment(containerId, fragment, addToStack, tag);
        }
    }

    @Override
    public void loadDialogFragment(int containerId, DialogFragment fragment, boolean addToStack) {
        loadDialogFragment(containerId, fragment, addToStack, fragment.getClass().getSimpleName());
    }

    @Override
    public void loadDialogFragment(int containerId, DialogFragment fragment, String tag) {
        loadDialogFragment(containerId, fragment, false, tag);
    }

    @Override
    public void loadDialogFragment(int containerId, DialogFragment fragment) {
        loadDialogFragment(containerId, fragment, false, fragment.getClass().getSimpleName());
    }


//    @Override
//    public void loadDialogFragment(DialogFragment fragment) {
//        fragment.show(getSupportFragmentManager(), null);
//    }
//
//    @Override
//    public void loadDialogFragment(DialogFragment fragment, String tag) {
//        fragment.show(getSupportFragmentManager(), tag);
//    }
//
//    @Override
//    public void loadDialogFragment(int containerId, DialogFragment fragment, String tag) {
//        if (DeviceUtils.isTablet(this)) {
//            loadDialogFragment(fragment, tag);
//        } else {
//            loadFragment(containerId, fragment, tag);
//        }
//    }
//
//    @Override
//    public void loadDialogFragment(int containerId, DialogFragment fragment) {
//        loadDialogFragment(containerId, fragment, null);
//    }

    public Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bIsTablet = getResources().getBoolean(R.bool.cu_isTablet);
        if (bIsTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setupActivityComponent();
//        buildAlertUtils();
    }

    @Override
    protected void onStart() {
//        if (baseActivityComponent == null) {
//            throw new IllegalStateException("Base Activity's onCreate must be called.");
//        }
        super.onStart();
    }

    /**
     * Disable overriding of Activity's default onBackPressed().
     * <p/>
     * Calls super's onBackPressed() if it wasn't handled by bActivity
     */
    @Override
    public final void onBackPressed() {

        boolean managed = false;
        if (bActivity instanceof OnDrawerStateListener) {
            managed = ((OnDrawerStateListener) bActivity).manageDrawerOnBackPressed();
        }

        if (!managed) {
            if (!(bActivity instanceof OnBackPressedListener && ((OnBackPressedListener) bActivity).onBackPressHandled())) {
                super.onBackPressed();
            }
        }
    }

    public void toast(String msg) {
        //SnackBarUtils.getSnackbar(this, msg).show();
    }

    public void alert(String msg) {
        //TODO alert with message only.
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (bActivity instanceof OnToolbarSetupListener) {
            super.setContentView(R.layout.cu_app_bar_main);
            ViewStub cu_id_stub = (ViewStub) findViewById(R.id.cu_id_stub);
            cu_id_stub.setLayoutResource(layoutResID);
            cu_id_stub.inflate();
            Toolbar toolbar = (Toolbar) findViewById(R.id.cu_id_toolbar);
            ((OnToolbarSetupListener) bActivity).setUpToolbar(toolbar);

        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        if (isLoggedIn()) {
            //TODO Restart Timer
        }
    }

    protected boolean isLoggedIn() {
        return false;
    }


    public interface OnToolbarSetupListener {
        void setUpToolbar(Toolbar toolbar);
    }

    public interface OnDrawerStateListener {
        boolean manageDrawerOnBackPressed();
    }

    protected void setupActivityComponent() {
    }

    ;

}
