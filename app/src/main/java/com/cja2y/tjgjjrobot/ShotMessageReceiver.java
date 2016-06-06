package com.cja2y.tjgjjrobot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ShotMessageReceiver extends BroadcastReceiver {

    public  void  onReceive(Context context, Intent intent)
    {
        //  判断是否为sendbroadcast发送的广播

        //Log.d("receive","receive");
        if  ("com.cja2y.accessibilitytest.MYBROADCAST" .equals(intent.getAction()))
        {
            Bundle bundle = intent.getExtras();
            if  (bundle != null )
            {
                String text = bundle.getString("text" );
                Toast.makeText(context, "接收天津服务广播：" + text, Toast.LENGTH_LONG).show();
                if(text!=null&&text.equals("shot")) {

                    EventBus.getDefault().post(
                            new FirstEvent("Screen shot"));
                }else if(text!=null&&text.equals("getlog")){
                    EventBus.getDefault().post(
                            new FirstEvent("Get log"));
                }else if(text!=null&&text.equals("starttask")){
                    EventBus.getDefault().post(
                            new FirstEvent("Start task"));
                }else if(text!=null&&text.equals("taskfailed")){
                    EventBus.getDefault().post(
                            new FirstEvent("Task failed"));
                }
            }
        }
    }
}
