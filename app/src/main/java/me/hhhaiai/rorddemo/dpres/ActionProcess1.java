package me.hhhaiai.rorddemo.dpres;

import android.content.Context;
import android.content.Intent;

import me.hhhaiai.androidrord.IRordCallback;
import me.hhhaiai.androidrord.utils.Logs;
import me.hhhaiai.rorddemo.MainActivity;

/**
 * @Copyright © 2022 sanbo Inc. All rights reserved.
 * @Description: process action1 and action11
 * send action shell:  adb shell am broadcast -a action1 --es msg 'Hello?'
 *   adb shell am broadcast -a action11 --es msg 'Hello?'
 * @Version: 1.0
 * @Create: 2022/02/50 20:43:58
 * @author: Administrator
 */
public class ActionProcess1 implements IRordCallback {
    /********************* get instance begin **************************/
    public static ActionProcess1 getInstance(Context context) {
        return HLODER.INSTANCE.initContext(context);
    }

    private ActionProcess1 initContext(Context context) {
        if (mContext == null && context != null) {
            mContext = context.getApplicationContext();
        }
        return HLODER.INSTANCE;
    }


    private static class HLODER {
        private static final ActionProcess1 INSTANCE = new ActionProcess1();
    }

    private ActionProcess1() {
    }

    private Context mContext = null;

    /********************* get instance end **************************/

    @Override
    public void onReceiverAction(String action, Intent intent) {
        StringBuffer sb = new StringBuffer();

        sb.append("===========ActionProcess1[").append(action).append("]=======\r\n").append(intent.toString());
        Logs.i(sb.toString());
        MainActivity.showMsg(sb.toString());
    }
}
