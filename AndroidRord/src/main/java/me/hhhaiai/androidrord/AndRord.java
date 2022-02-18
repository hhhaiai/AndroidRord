package me.hhhaiai.androidrord;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import me.hhhaiai.androidrord.impl.RordImpl;
import me.hhhaiai.androidrord.utils.Logs;

public class AndRord {
    public static void init(Context context, String action, IRordCallback callback) {
        try {
            if (isInvalid(context, callback)) {
                return;
            }
            init(context.getApplicationContext(), new ArrayList<String>() {{
                if (!TextUtils.isEmpty(action)) {
                    add(action);
                }
            }}, callback);
        } catch (Throwable e) {
            Logs.e(e);
        }
    }

    public static void init(Context context, List<String> actions, IRordCallback callback) {
        try {
            if (isInvalid(context, callback)) {
                return;
            }
            if (actions == null || actions.size() < 1) {
                Logs.e("the aciton is null, will return!");
                return;
            }
            RordImpl.init(context.getApplicationContext(), actions, callback);
        } catch (Throwable e) {
            Logs.e(e);
        }
    }

    private static boolean isInvalid(Context context, IRordCallback callback) {
        if (context == null) {
            Logs.i("the context is null, will return!");
            return true;
        }

        if (callback == null) {
            Logs.i("the callback is null, will return!");
            return true;
        }
        return false;
    }
}
