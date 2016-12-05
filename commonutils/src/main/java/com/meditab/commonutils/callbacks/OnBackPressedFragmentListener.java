/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.callbacks;

import com.meditab.commonutils.base.BaseDialogFragment;
import com.meditab.commonutils.base.BaseFragment;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 8/6/16 7:21 PM.
 */

public interface OnBackPressedFragmentListener extends OnBackPressedListener {
    void setCurrentFragment(BaseFragment currentFragment);

    void setCurrentFragment(BaseDialogFragment currentFragment);
}
