package com.meditab.commonutils.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meditab.commonutils.base.BaseFragment;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/7/16 6:36 PM
 */
public abstract class ParentFragment<T> extends BaseFragment<T> {

    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHaveComponent<T>) getActivity()).getComponent());
    }

    protected abstract void setupFragmentComponent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent();
    }
}
