package me.hhhaiai.rorddemo;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import me.hhhaiai.androidrord.AndRord;
import me.hhhaiai.androidrord.IRordCallback;
import me.hhhaiai.rorddemo.dpres.ActionProcess1;
import me.hhhaiai.rorddemo.dpres.ActionProcess2;

public class DemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Map<String, IRordCallback> map = new HashMap<String, IRordCallback>();
        map.put("action1", ActionProcess1.getInstance(base));
        map.put("action11", ActionProcess1.getInstance(base));
        map.put("action2", ActionProcess2.getInstance(base));
        map.put("action22", ActionProcess2.getInstance(base));
        AndRord.init(base, map);
    }
}
