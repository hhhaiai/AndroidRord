package me.hhhaiai.androidrord.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import me.hhhaiai.androidrord.IRordCallback;
import me.hhhaiai.androidrord.recv.ReceiverUtils;


public class RordImpl {
    public static IRordCallback CALLBACK = null;
    public static List<String> ACTIONS = new ArrayList<String>();

    public static void init(Context context, List<String> actions, IRordCallback callback) {
        ReceiverUtils.getInstance().registAllReceiver(context, actions);
        CALLBACK = callback;
        ACTIONS = actions;
    }

    public static void stop(Context context) {
        ReceiverUtils.getInstance().unRegistAllReceiver(context);
    }
}
