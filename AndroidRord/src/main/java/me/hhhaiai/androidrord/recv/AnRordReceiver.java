package me.hhhaiai.androidrord.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import me.hhhaiai.androidrord.impl.RordImpl;
import me.hhhaiai.androidrord.utils.Logs;

public class AnRordReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

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
            if (RordImpl.ACTIONS.contains(action)) {
                // has  custom action, will process.
                if (RordImpl.CALLBACK == null) {
                    Logs.e("Receiver action[" + action + "], the callback is null, will return!");
                    return;
                }
                RordImpl.CALLBACK.onReceiverAction(action, new Intent(intent));
            }

        } catch (Throwable e) {
            Logs.e(e);
        }
    }
}