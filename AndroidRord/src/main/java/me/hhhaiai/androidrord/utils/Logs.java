package me.hhhaiai.androidrord.utils;

import android.text.TextUtils;
import android.util.Log;

public class Logs {
    private static String TAG = "AndRord";

    public static void v(String msg) {
        print(Log.VERBOSE, TAG, msg, null);
    }

    public static void d(String msg) {
        print(Log.DEBUG, TAG, msg, null);
    }

    public static void i(String msg) {
        print(Log.INFO, TAG, msg, null);
    }

    public static void w(String msg) {
        print(Log.WARN, TAG, msg, null);
    }

    public static void e(String msg) {
        print(Log.ERROR, TAG, msg, null);
    }

    public static void wtf(String msg) {
        print(Log.ASSERT, TAG, msg, null);
    }

    public static void e(Throwable e) {
        print(Log.ERROR, TAG, null, Log.getStackTraceString(e));
    }


    public static void print(int priority, String tag, String info, String error) {

        String msg = "";
        if (!TextUtils.isEmpty(info)) {
            msg += info + "\r\n";
        }
        if (!TextUtils.isEmpty(error)) {
            msg += error + "\r\n";
        }
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.println(priority, tag, msg);
    }
}
