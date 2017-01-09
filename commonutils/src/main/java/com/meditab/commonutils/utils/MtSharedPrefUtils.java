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

public class MtSharedPrefUtils {

    //pref file database.xml
    //TODO place in constants file

    public static final String PREF_FILE_DEFAULT = "default";


    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public static void setValue(Context context, String prefsKey, String
            prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context, PREF_FILE_DEFAULT);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public static void setValue(Context context, String prefsKey, int
            prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context, PREF_FILE_DEFAULT);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:   key of the preference object
     * @param prefsValue: value of the preference object
     */
    public static void setValue(Context context, String prefsKey, boolean prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context, PREF_FILE_DEFAULT);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public static void setValue(Context context, String prefsKey, String
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(context, strFileName);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public static void setValue(Context context, String prefsKey, int
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(context, strFileName);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param prefsKey:    key of the preference object
     * @param prefsValue:  value of the preference object
     * @param strFileName: preference file name
     */
    public static void setValue(Context context, String prefsKey, boolean
            prefsValue, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(context, strFileName);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference string
     */
    public static String getValue(Context context, String key, String
            defaultValue) {
        SharedPreferences sp = getPreferences(context, PREF_FILE_DEFAULT);
        return sp.getString(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference boolean
     */
    public static boolean getValue(Context context, String key, boolean
            defaultValue) {
        SharedPreferences sp = getPreferences(context, PREF_FILE_DEFAULT);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @return preference integer
     */
    public static int getValue(Context context, String key, int
            defaultValue) {
        SharedPreferences sp = getPreferences(context, PREF_FILE_DEFAULT);
        return sp.getInt(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference string
     */
    public static String getValue(Context context, String key, String
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(context, strFileName);
        return sp.getString(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference boolean
     */
    public static boolean getValue(Context context, String key, boolean
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(context, strFileName);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * @param key:          key name of the object to be fetched
     * @param defaultValue: default value of the object
     * @param strFileName:  Name of Preference file
     * @return preference integer
     */
    public static int getValue(Context context, String key, int
            defaultValue, String strFileName) {
        SharedPreferences sp = getPreferences(context, strFileName);
        return sp.getInt(key, defaultValue);
    }

    /**
     * @param context:           bContext of the calling bActivity
     * @param strPreferenceFile: Shared Preferences File Name
     * @return SharedPreferences
     */
    private static SharedPreferences.Editor getPrefsEditor(Context context,
                                                           String strPreferenceFile) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(strPreferenceFile,
                Context.MODE_PRIVATE);

        return sharedPreferences.edit();
    }

    /**
     * @param context:           bContext of the calling bActivity
     * @param strPreferenceFile: Shared Preferences File Name
     * @return SharedPreferences
     */
    private static SharedPreferences getPreferences(Context context,
                                                    String strPreferenceFile) {
        return context.getSharedPreferences(strPreferenceFile, Context.MODE_PRIVATE);
    }


    /**
     * clear pref file
     *
     * @param context
     * @param strFileName pref file name to be cleared
     */
    public static void clearPrefFile(Context context, String strFileName) {
        SharedPreferences.Editor editor = getPrefsEditor(context, strFileName);
        editor.clear();
        editor.commit();
    }

}
