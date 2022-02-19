package me.hhhaiai.androidrord.impl;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hhhaiai.androidrord.IRordCallback;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: process simple
 * @Version: 1.0
 * @Create: 2022/02/50 22:00:11
 * @author: Administrator
 */
public class RordImpl {

    public static Map<String, IRordCallback> MAPPING_MAP = new HashMap<String, IRordCallback>();

    /**
     * support one callback and more action
     * @param context
     * @param actions
     * @param callback
     */
    public static void init(Context context, List<String> actions, IRordCallback callback) {
        parserAndGeneralMapping(actions, callback);
        ActionsRegister.getInstance().addActions(context, actions);
    }


    public static void stop(Context context) {
        ActionsRegister.getInstance().unRegistAllReceiver(context);
    }

    /**
     * support more action and callback
     * @param context
     * @param actionAndCallback
     */
    public static void init(Context context, Map<String, IRordCallback> actionAndCallback) {

        List<String> actions = new ArrayList<String>(actionAndCallback.keySet());
        parserAndGeneralMapping(actions, actionAndCallback);
        ActionsRegister.getInstance().addActions(context, actions);
    }


    private static void parserAndGeneralMapping(List<String> actions, IRordCallback callback) {
        for (String action : actions) {
            if (!TextUtils.isEmpty(action)) {
                MAPPING_MAP.put(action, callback);
            }
        }
    }

    private static void parserAndGeneralMapping(List<String> actions, Map<String, IRordCallback> actionAndCallback) {
        for (String action : actions) {
            if (!TextUtils.isEmpty(action)) {
                MAPPING_MAP.put(action, actionAndCallback.get(action));
            }
        }
    }


}
