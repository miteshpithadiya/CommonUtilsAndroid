package com.meditab.commonutils.parent;

import android.os.Bundle;

import com.meditab.commonutils.base.BaseActivity;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/7/16 6:16 PM
 */
public abstract class ParentActivity<T> extends BaseActivity<T> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();
}
