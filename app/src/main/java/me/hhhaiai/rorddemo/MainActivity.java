package me.hhhaiai.rorddemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import me.hhhaiai.androidrord.utils.Logs;
import me.hhhaiai.rorddemo.utils.CRelativeLayout;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private CRelativeLayout mLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new CRelativeLayout(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 1:
                        Logs.i("click one");
                        break;
                    default:
                        break;
                }
            }
        }, 4, 10);
        setContentView(mLayout);
    }
}