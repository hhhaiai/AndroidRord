package me.hhhaiai.androidrord.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: log utils. support print log and error.
 *     support more tag:
 *     temp tag--> one log one set
 *     keep tag--> one log  not clear util clear keep tag
 *     default tag--> not set tag has default tag
 * @Version: 1.0
 * @Create: 2022/02/50 21:48:54
 * @author: Administrator
 */
public class Logs {
    private Logs() {
    }

    public static final Logs info = new Logs();
    private static String TAG = "AndRord";
    private static String mTempTag = null;
    private static String mKeepTag = null;

    public static Logs tag(String tag) {
        info.mTempTag = tag;
        return info;
    }

    public static void keepTag(String tag) {
        info.mKeepTag = tag;
    }

    public static void clearKeppTag() {
        info.mKeepTag = null;
    }

    /**
     *  priority: keeptag> temptag >default
     * @return
     */
    public static String getCurrentTag() {
        if (!TextUtils.isEmpty(info.mKeepTag)) {
            return info.mKeepTag;
        } else {
            if (!TextUtils.isEmpty(info.mTempTag)) {
                return info.mTempTag;
            } else {
                return info.TAG;
            }
        }
    }


    public static void v(String msg) {
        info.print(Log.VERBOSE, msg, null);
    }

    public static void d(String msg) {
        info.print(Log.DEBUG, msg, null);
    }

    public static void i(String msg) {
        info.print(Log.INFO, msg, null);
    }
    public static void i(Number msg) {
        info.print(Log.INFO, msg.toString(), null);
    }

    public static void w(String msg) {
        info.print(Log.WARN, msg, null);
    }

    public static void e(String msg) {
        info.print(Log.ERROR, msg, null);
    }

    public static void wtf(String msg) {
        info.print(Log.ASSERT, msg, null);
    }

    public static void e(Throwable e) {
        info.print(Log.ERROR, null, Log.getStackTraceString(e));
    }


    public void print(int priority, String info, String error) {
        try {
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
            Log.println(priority, getCurrentTag(), msg);
        } catch (Throwable e) {
        } finally {
            mTempTag = null;
        }
    }
}
