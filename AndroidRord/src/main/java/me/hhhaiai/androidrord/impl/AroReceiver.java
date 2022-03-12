package me.hhhaiai.androidrord.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import me.hhhaiai.androidrord.IRordCallback;
import me.hhhaiai.androidrord.utils.Logs;

public class AroReceiver extends BroadcastReceiver {
//    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // makesure register
        ActionsRegister.getInstance().registAllReceiver(context);

        //makesure Time-consuming tasks can work
//        executor.submit(() -> {
//            runInThread(intent);
//        });

        new Thread(() -> {
            runInThread(intent);
        }).start();
    }

    private void runInThread(Intent intent) {
        try {
            if (intent == null) {
                Logs.e("Receive intent is null");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                Logs.e("Receiver action is null! intent:" + intent);
                return;
            }
            // just found support actions
            if (RordImpl.MAPPING_MAP.containsKey(action)) {
                IRordCallback callback = RordImpl.MAPPING_MAP.get(action);
                // has  custom action, will process.
                if (callback == null) {
                    Logs.e("Receiver action[" + action + "], the callback is null, will return!");
                } else {
                    callback.onReceiverAction(action, new Intent(intent));
                }
            }
        } catch (Throwable e) {
            Logs.e(e);
        }
    }
}