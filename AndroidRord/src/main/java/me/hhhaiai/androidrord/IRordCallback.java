package me.hhhaiai.androidrord;

import android.content.Intent;

public interface IRordCallback {
    public abstract void onReceiverAction(String action, Intent intent);
}
