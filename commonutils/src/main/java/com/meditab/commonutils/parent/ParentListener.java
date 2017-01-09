package com.meditab.commonutils.parent;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 8/9/16 3:04 PM
 */
public interface ParentListener {

    void onNoInternetAvailable();

    void onShowProgressDialog(String message);

    void onHideProgressDialog();
}
