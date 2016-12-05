package com.meditab.commonutils.parent;

import com.meditab.commonutils.base.BaseDialogFragment;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/7/16 6:36 PM
 */
public abstract class ParentDialogFragment<T> extends BaseDialogFragment<T> {

    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHaveComponent<T>) getActivity()).getComponent());
    }

}
