package com.yashka.dedofiom.Utill;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPre {
    private static String DONT_SHOW_AGAIN_UPGRAD = "dont_show_again_upgrad";

    public static void setUpgradPremiumDontShow(Context context, boolean z) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(DONT_SHOW_AGAIN_UPGRAD, z);
        edit.apply();
    }

    public static boolean getUpgradPremiumDontShow(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(DONT_SHOW_AGAIN_UPGRAD, false);
    }
}
