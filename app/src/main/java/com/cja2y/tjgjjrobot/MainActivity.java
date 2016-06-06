package com.cja2y.tjgjjrobot;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;

import android.os.Bundle;
import android.widget.Toast;



import org.json.JSONObject;

public class MainActivity extends Activity {
    private int result = 0;
    private Intent intent = null;
    private int REQUEST_MEDIA_PROJECTION = 1;
    private MediaProjectionManager mMediaProjectionManager;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaProjectionManager = (MediaProjectionManager) getApplication().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        startIntent();
        //startService(new Intent(getApplicationContext(), Service2.class));


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startIntent() {
        if (intent != null && result != 0) {
            ((ShotApplication) getApplication()).setResult(result);
            ((ShotApplication) getApplication()).setIntent(intent);
            Intent intent = new Intent(getApplicationContext(), Service1.class);
            startService(intent);
        } else {
            startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
            ((ShotApplication) getApplication()).setMediaProjectionManager(mMediaProjectionManager);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            } else if (data != null && resultCode != 0) {
                result = resultCode;
                intent = data;
                ((ShotApplication) getApplication()).setResult(resultCode);
                ((ShotApplication) getApplication()).setIntent(data);
                Intent intent = new Intent(getApplicationContext(), Service1.class);
                startService(intent);

                finish();
            }
        }
    }
}
