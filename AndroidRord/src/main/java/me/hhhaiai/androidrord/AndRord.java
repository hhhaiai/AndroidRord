package me.hhhaiai.androidrord;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.hhhaiai.androidrord.impl.RordImpl;
import me.hhhaiai.androidrord.utils.Logs;
import me.hhhaiai.androidrord.utils.Utils;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: init API
 * @Version: 1.0
 * @Create: 2022/02/50 21:43:41
 * @author: Administrator
 */
public class AndRord {
    /**
     * init support one action and one callback
     * @param context
     * @param action
     * @param callback
     */
    public static void init(Context context, String action, IRordCallback callback) {
        try {
            if (!TextUtils.isEmpty(action)) {
                init(context, new ArrayList<String>() {{
                    add(action);
                }}, callback);
            }

        } catch (Throwable e) {
            Logs.e(e);
        }
    }

    /**
     * init support more action and one callback
     * @param context
     * @param actions
     * @param callback
     */
    public static void init(Context context, List<String> actions, IRordCallback callback) {
        try {
            if (!Utils.isLegitimate(context, actions, callback)) {
                Logs.e("init failed, args is error!");
                return;
            }
            RordImpl.init(context, actions, callback);
        } catch (Throwable e) {
            Logs.e(e);
        }
    }

    /**
     * support more action and callback
     * @param context
     * @param actionAndCallback
     */
    public static void init(Context context, Map<String, IRordCallback> actionAndCallback) {
        try {
            if (!Utils.isLegitimate(context, actionAndCallback)) {
                Logs.e("init failed, args is error!");
                return;
            }
            RordImpl.init(context, actionAndCallback);
        } catch (Throwable e) {
            Logs.e(e);
        }
    }

}
