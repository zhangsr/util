package com.cvte.sdk.update.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cvte.sdk.update.Constant;

import java.io.File;

/**
 * @description: Api for config storage
 * @author: Saul
 * @date: 14-11-11
 * @version: 1.0
 */
public class PreferenceUtil {
    public static final String SP_KEY_IGNORE_VERSION = "ignore_version";
    public static final String SP_KEY_DOWNLOAD_PATH = "download_path";
    public static final String SP_KEY_CACHE_SIZE = "cache_size";
    public static final String SP_KEY_UPDATE_AUTO_POPUP = "update_auto_popup";
    public static final String SP_KEY_DOWNLOAD_AUTO_INSTALL = "download_auto_install";

    public static void setUpdateAutoPopup(Context context, boolean updateAutoPopup) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_KEY_UPDATE_AUTO_POPUP, updateAutoPopup);
        editor.apply();
    }

    public static boolean isUpdateAutoPopup(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(SP_KEY_UPDATE_AUTO_POPUP, true);
    }

    public static void setDownloadPath(Context context, String path) {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_KEY_DOWNLOAD_PATH, path);
        editor.apply();
    }

    public static String getDownloadPath(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SP_KEY_DOWNLOAD_PATH,
                //FIXME 14-11-11 redundancy define
                context.getCacheDir() + File.separator + ".mengyou" + File.separator);
    }

    public static void setIgnoreVersion(Context context, int version) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(SP_KEY_IGNORE_VERSION, version);
        editor.apply();
    }

    public static int getIgnoreVersion(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(SP_KEY_IGNORE_VERSION, -1);
    }

    public static void setCacheSize(Context context, long sizeInByte) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(SP_KEY_CACHE_SIZE, sizeInByte);
        editor.apply();
    }

    public static long getCacheSize(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(SP_KEY_CACHE_SIZE,
                Constant.CACHE_CLEAR_THRESHOLD);
    }

    public static void setDownloadAutoInstall(Context context, boolean autoInstall) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_KEY_DOWNLOAD_AUTO_INSTALL, autoInstall);
        editor.apply();
    }

    public static boolean isDownloadAutoInstall(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(SP_KEY_DOWNLOAD_AUTO_INSTALL, true);
    }
}
