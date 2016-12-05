/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.callbacks;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 8/6/16 3:09 PM.
 */

public interface FragmentLoader {
    void loadFragment(int containerId, Fragment fragment, boolean addToStack, String tag);

    void loadFragment(int containerId, Fragment fragment, boolean addToStack);

    void loadFragment(int containerId, Fragment fragment, String tag);

    void loadFragment(int containerId, Fragment fragment);

    void loadDialogFragment(int containerId,DialogFragment fragment,boolean addToStack, String tag);

    void loadDialogFragment(int containerId,DialogFragment fragment, boolean addToStack);

    void loadDialogFragment(int containerId,DialogFragment fragment, String tag);

    void loadDialogFragment(int containerId,DialogFragment fragment);

}
