/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p/>
 *         Created on 20/5/16 7:19 PM.
 */

public class SharedPrefUtils {

    //pref file database.xml
    //TODO place in constants file

    public static final String PREF_FILE_DATABASE = "database";
    public static final String PREF_FILE_FIREBASE = "firebase";

    //keys for database.xml

    public static final String PREF_KEY_DB_VERSION = "db_version";
    public static final String PREF_KEY_DB_LEVEL = "db_level";

    public static final String PREF_FILE_DEFAULT = "default";

    private Context mContext;

    public SharedPrefUtils(Context context) {
        this.mContext = context;
    }

    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public void setValue(String prefsKey, String
            prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, PREF_FILE_DEFAULT);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public void setValue(String prefsKey, int
            prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, PREF_FILE_DEFAULT);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public void setValue(String prefsKey, boolean prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, PREF_FILE_DEFAULT);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public void setValue(String prefsKey, String
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, strFileName);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public void setValue(String prefsKey, int
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, strFileName);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public void setValue(String prefsKey, boolean
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(mContext, strFileName);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference string
     */
    public String getValue(String key, String
            defaultValue) {
        SharedPreferences sp = getPreferences(mContext, PREF_FILE_DEFAULT);
        return sp.getString(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference boolean
     */
    public boolean getValue(String key, boolean
            defaultValue) {
        SharedPreferences sp = getPreferences(mContext, PREF_FILE_DEFAULT);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference integer
     */
    public int getValue(String key, int
            defaultValue) {
        SharedPreferences sp = getPreferences(mContext, PREF_FILE_DEFAULT);
        return sp.getInt(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference string
     */
    public String getValue(String key, String
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(mContext, strFileName);
        return sp.getString(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference boolean
     */
    public boolean getValue(String key, boolean
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(mContext, strFileName);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference integer
     */
    public int getValue(String key, int
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(mContext, strFileName);
        return sp.getInt(key, defaultValue);
    }

    /**
     * @param mContext:          bContext of the calling bActivity
     * @param strPreferenceFile: Shared Preferences File Name
     * @return SharedPreferences
     */
    private SharedPreferences.Editor getPrefsEditor(Context mContext,
                                                    String strPreferenceFile) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(strPreferenceFile,
                Context.MODE_PRIVATE);

        return sharedPreferences.edit();
    }

    /**
     * @param mContext:          bContext of the calling bActivity
     * @param strPreferenceFile: Shared Preferences File Name
     * @return SharedPreferences
     */
    private SharedPreferences getPreferences(Context mContext,
                                             String strPreferenceFile) {
        return mContext.getSharedPreferences(strPreferenceFile, Context.MODE_PRIVATE);
    }

}
