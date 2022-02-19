package me.hhhaiai.androidrord.impl;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import java.util.List;

import me.hhhaiai.androidrord.utils.Logs;


class ActionsRegister {
    private AroReceiver mReceiver = null;

    @SuppressWarnings("unused")
    private boolean sWorkStatus = false;

    private ActionsRegister() {
    }

    public static ActionsRegister getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * support  more init . more set actions
     * @param context
     * @param actions
     */
    public void addActions(Context context, List<String> actions) {
        registAllReceiver(context);
        if (actions == null || actions.size() < 1) {
            Logs.i("the action is null!");
            return;
        }
        // custiom
        IntentFilter intentFilter = new IntentFilter();
        for (String custionAction : actions) {
            intentFilter.addAction(custionAction);
        }
        intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        context.registerReceiver(mReceiver, intentFilter);
    }

    @SuppressWarnings("deprecation")
    public void registAllReceiver(Context context) {
        try {
            setWork(true);
            if (mReceiver == null) {
                mReceiver = new AroReceiver();
                IntentFilter intentFilter = new IntentFilter();
                if (Build.VERSION.SDK_INT < 24) {
                    intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                    intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                    context.registerReceiver(mReceiver, intentFilter);
                }
                // 启动
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 连接、断开电源
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
                intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 解锁唤醒
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_USER_PRESENT);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 增删改查
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
                intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
                intentFilter.addDataScheme("package");
                context.registerReceiver(mReceiver, intentFilter);

                // 关屏、锁屏
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
                intentFilter.addAction(Intent.ACTION_SCREEN_ON);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 高版本兼容  时间修改
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);
                // 高版本兼容  时区修改
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);
                // 高版本兼容  location修改
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 高版本兼容  闹铃修改
                intentFilter = new IntentFilter();
                intentFilter.addAction("android.app.action.NEXT_ALARM_CLOCK_CHANGED");
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // battery
                intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);
                intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
                intentFilter.addAction("android.intent.action.BATTERY_LEVEL_CHANGED");
                intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

                // 用户切换身份
                intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.USER_INITIALIZE");
                intentFilter.addAction("android.intent.action.USER_FOREGROUND");
                intentFilter.addAction("android.intent.action.USER_BACKGROUND");
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);


                intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.ALL_APPS");
                intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
                intentFilter.addAction("android.intent.action.ANSWER");
                intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
                context.registerReceiver(mReceiver, intentFilter);

            }
        } catch (Throwable e) {
            setWork(false);
            Logs.e(e);
        }
    }

    public void unRegistAllReceiver(Context context) {
        try {
            setWork(false);
            context.unregisterReceiver(mReceiver);
            mReceiver = null;
        } catch (Throwable e) {
            Logs.e(e);
        }
    }

    /**
     * @param isWorking
     */
    public void setWork(boolean isWorking) {
        sWorkStatus = isWorking;
    }

    private static class Holder {
        private static final ActionsRegister INSTANCE = new ActionsRegister();
    }
}
