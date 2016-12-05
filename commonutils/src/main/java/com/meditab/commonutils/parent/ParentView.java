package com.meditab.commonutils.parent;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 8/5/16 6:31 PM
 */
public interface ParentView {
    void showNoInternetAlert();

    void showProgressDialog(String message);

    void hideProgressDialog();
}
