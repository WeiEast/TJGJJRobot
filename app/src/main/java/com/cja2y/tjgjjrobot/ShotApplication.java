package com.cja2y.tjgjjrobot;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;

/**
 * Created by cja2y on 2016/4/7 learn from csdn
 */
public class ShotApplication extends Application {
    private int result;
    private Intent intent;
    public static Context mContext;
    private MediaProjectionManager mMediaProjectionManager;


    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public int getResult() {
        return result;
    }

    public Intent getIntent() {
        return intent;
    }

    public MediaProjectionManager getMediaProjectionManager() {
        return mMediaProjectionManager;
    }

    public void setResult(int result1) {
        this.result = result1;
    }

    public void setIntent(Intent intent1) {
        this.intent = intent1;
    }

    public void setMediaProjectionManager(MediaProjectionManager mMediaProjectionManager) {
        this.mMediaProjectionManager = mMediaProjectionManager;
    }
}
