package me.hhhaiai.rorddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import me.hhhaiai.androidrord.utils.Logs;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static CRelativeLayout mLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new CRelativeLayout(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 1:
//                        exec("am broadcast -a action1 --es msg1 'hello action1'");
                        sendBroadcastCustom("action1");
                        break;
                    case 2:
//                        exec("am broadcast -a action11 --es msg11 'hello action11'");
                        sendBroadcastCustom("action11");
                        break;
                    case 3:
//                        exec("am broadcast -a action2 --es msg2 'hello action2'");
                        sendBroadcastCustom("action2");
                        break;
                    case 4:
//                        exec("am broadcast -a action22 --es msg22 'hello action22'");
                        sendBroadcastCustom("action22");
                        break;


                    case 5:
                        exec("am broadcast -a action1 --es msg1 'hello action1'");
//                        sendBroadcastCustom("action1");
                        break;
                    case 6:
                        exec("am broadcast -a action11 --es msg11 'hello action11'");
//                        sendBroadcastCustom("action11");
                        break;
                    case 7:
                        exec("am broadcast -a action2 --es msg2 'hello action2'");
//                        sendBroadcastCustom("action2");
                        break;
                    case 8:
                        exec("am broadcast -a action22 --es msg22 'hello action22'");
//                        sendBroadcastCustom("action22");
                        break;
                    default:
                        break;
                }
            }
        }, 4, 10);
        setContentView(mLayout);
    }

    public static void showMsg(String info) {
        if (!TextUtils.isEmpty(info)) {
            mLayout.showInPage(info);
        } else {
            Logs.e("MainActivity the show msg if null!");
        }
    }

    /**
     * can work
     * @param action
     */
    private void sendBroadcastCustom(String action) {
        Intent i = new Intent(action);
        i.setPackage(getPackageName());
        i.putExtra("msg", "this is data from  " + action);
        i.setAction(action);
        i.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        sendBroadcast(i);
        Logs.i("sendBroadcast over");
    }

    // shell send command .
    // can't work
    //eg: am broadcast -a action1 --es msg1 'hello action1'
    private void exec(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            Logs.e(e);
        }
        Logs.i("shell send msg over.");
    }
}