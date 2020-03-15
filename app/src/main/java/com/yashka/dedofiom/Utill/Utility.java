package com.yashka.dedofiom.Utill;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.PrintStream;
import java.util.List;

public class Utility {
    public static int ADD_KEY_COUNT = 31;
    public static int DEVICE = 1;
    public static int HIDE_ADS_PERMENETLY = 2;
    public static int HIDE_ADS_TEMPORY = 1;
    public static int LOCAL = 1;
    public static int NOT_REFUND = 0;
    public static int READ_FEED = 3;
    public static int READ_FEILD = 2;
    public static int REFUND = 1;
    public static int SENSOR = 2;
    public static int SHOW_ADS = 0;
    public static int THINGSPEAK = 2;
    public static int WRITE_FEED = 1;
    LinearLayout linearLayout;

    public static int getWith(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        int round = Math.round(((float) displayMetrics.widthPixels) / displayMetrics.density);
        Math.round((float) displayMetrics.widthPixels);
        return round;
    }

    public static int getHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        int round = Math.round(((float) displayMetrics.heightPixels) / displayMetrics.density);
        Math.round((float) displayMetrics.widthPixels);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("apap ");
        sb.append(round);
        printStream.println(sb.toString());
        return round;
    }

    public static Integer getImageId(String str, Context context) {
        if (str == null) {
            return null;
        }
        return Integer.valueOf(context.getResources().getIdentifier(str, "drawable", context.getPackageName()));
    }

    public static int getLayoutHeightandWith(Context context, View view, final int i) {
        final int[] iArr = {0};
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            private View layout;

            public void onGlobalLayout() {
                this.layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int measuredWidth = this.layout.getMeasuredWidth();
                int measuredHeight = this.layout.getMeasuredHeight();
                if (i == 0) {
                    iArr[0] = measuredWidth;
                } else {
                    iArr[0] = measuredHeight;
                }
            }
        });
        return iArr[0];
    }

    public static int getIndexOfStringList(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (((String) list.get(i)).equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public static Typeface getFontBashitha(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
