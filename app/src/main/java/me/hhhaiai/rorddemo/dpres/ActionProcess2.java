package me.hhhaiai.rorddemo.dpres;

import android.content.Context;
import android.content.Intent;

import me.hhhaiai.androidrord.IRordCallback;
import me.hhhaiai.androidrord.utils.Logs;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: process action2 and action22
 * send action2 shell: adb shell am broadcast -a action2 --es msg 'Hello?'
 * adb shell am broadcast -a action22 --es msg 'Hello?'
 * @Version: 1.0
 * @Create: 2022/02/50 20:46:10
 * @author: Administrator
 */
public class ActionProcess2 implements IRordCallback {
    /********************* get instance begin **************************/
    public static ActionProcess2 getInstance(Context context) {
        return HLODER.INSTANCE.initContext(context);
    }

    private ActionProcess2 initContext(Context context) {
        if (mContext == null && context != null) {
            mContext = context.getApplicationContext();
        }
        return HLODER.INSTANCE;
    }

    private static class HLODER {
        private static final ActionProcess2 INSTANCE = new ActionProcess2();
    }

    private ActionProcess2() {
    }

    private Context mContext = null;

    /********************* get instance end **************************/

    @Override
    public void onReceiverAction(String action, Intent intent) {
        Logs.i("ActionProcess1:" + intent);
    }
}
