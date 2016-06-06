package com.cja2y.tjgjjrobot;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cja2y.tjgjjrobot.async.ToolKit;
import com.cja2y.tjgjjrobot.ghostlog.LogLine;
import com.cja2y.tjgjjrobot.ghostlog.LogReaderAsyncTask;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.commons.lang.StringEscapeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Administrator on 2016/4/7.
 */
public class Service1 extends Service {
    private LinearLayout mFloatLayout = null;
    private WindowManager.LayoutParams wmParams = null;
    private WindowManager mWindowManager = null;
    private LayoutInflater inflater = null;
    private ImageButton mFloatView = null;

    private static final String TAG = "MainActivity";

    private SimpleDateFormat dateFormat = null;
    private String strDate = null;
    private String pathImage = null;
    private String nameImage = null;

    private MediaProjection mMediaProjection = null;
    private VirtualDisplay mVirtualDisplay = null;

    public static int mResultCode = 0;
    public static Intent mResultData = null;
    public static MediaProjectionManager mMediaProjectionManager1 = null;

    private WindowManager mWindowManager1 = null;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private ImageReader mImageReader = null;
    private DisplayMetrics metrics = null;
    private int mScreenDensity = 0;


    private static final int MSG_SUM = 0x110;

    private String LogTag = "HttpUtil";

    public JSONObject loginInfoJson = null;

    private Boolean jobState = true;

    private int mPid;
    public static String start_date = "";
    public static String detail_start_date = "";
    public int threadIndex = 0;
    public ScheduledExecutorService scheduledThreadPool;
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {
            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what) {
                //msg 客户端传来的消息
                case MSG_SUM:
                    msgToClient.what = MSG_SUM;
                    saveCapture();
//                    try {
//                        //模拟耗时
//                        Thread.sleep(2000);
//                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
//                        msgfromClient.replyTo.send(msgToClient);
//
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        // return null;
        return mMessenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        EventBus.getDefault().register(this);
        createFloatView();

        createVirtualEnvironment();
        mPid = android.os.Process.myPid();
//        startTimer();
       // logContent = new StringBuffer();
        //getLog2();
        detailArray=null;
        gjjGeneralJson = null;
        job_sid = null;
        startLogListen();

         scheduledThreadPool = Executors.newScheduledThreadPool(50);
        loginGjj();

        //sendAccountInfoToAccessbilty();
    }





    @Deprecated
    public void onStart(Intent intent, int startId) {


    }


    private int timerIndex = 1;
    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 100;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        inflater = LayoutInflater.from(getApplication());
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
        mWindowManager.addView(mFloatLayout, wmParams);
        mFloatView = (ImageButton) mFloatLayout.findViewById(R.id.float_id);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth() / 2;
                wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight() / 2 - 25;
                mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                return false;
            }
        });

        mFloatView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                detailArray=null;
                gjjGeneralJson = null;
                if(timerIndex==1) {
                    startTimer();
                    timerIndex=-1;
                }else if(timerIndex ==-1){
                    stopTimer();
                    timerIndex=1;
                }
                //loginGjj();
               // HttpClient.tjAppLogin(Service1.this);
                // hide the button
//                mFloatView.setVisibility(View.INVISIBLE);
//
//                Handler handler1 = new Handler();
//                handler1.postDelayed(new Runnable() {
//                    public void run() {
//                        //start virtual
//                        startVirtual();
//                    }
//                }, 500);
//
//                Handler handler2 = new Handler();
//                handler2.postDelayed(new Runnable() {
//                    public void run() {
//                        //capture the screen
//                        startCapture();
//                    }
//                }, 1500);
//
//                Handler handler3 = new Handler();
//                handler3.postDelayed(new Runnable() {
//                    public void run() {
//                        mFloatView.setVisibility(View.VISIBLE);
//                        //stopVirtual();
//                    }
//                }, 1000);
//                ActivityManager mAm = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                //  mAm.forceStopPackage("xxx.xxx.xxx");
//                mAm.killBackgroundProcesses("com.cja2y.accessibilitytest");
//                ToolKit.runOnMainThreadAsync(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(Service1.this,"kill tj app service",Toast.LENGTH_LONG).show();
//                    }
//                });


            }
        });

       // Log.i(TAG, "created the float sphere view");
    }

    private void startLogListen(){
      //  Toast.makeText(Service1.this, "begin catch log: ", Toast.LENGTH_SHORT).show();
        new LogReaderAsyncTask(){
            @Override
            protected void onProgressUpdate(LogLine... values) {
                LogLine line = values[0];
//                if(line.getPid() == mPid && TAG.equals(line.getTag()) && line.getMessage().startsWith("lalala") ){
                if(LogTag.equals(line.getTag())) {

//                    TJGjjDataUtil.generalGjjDataTransition(line.getMessage());
//                    TJGjjDataUtil.detailGjjDataTransition(line.getMessage());
                    dataTransition(line.getMessage());
                    if(gjjGeneralJson!=null&&detailArray!=null){

                        System.out.println("catch log robot post " + gjjGeneralJson.toString() + "***" + detailArray.toString());
                        Toast.makeText(Service1.this, "catch data general: " + detailArray.toString()+"catch data general "+gjjGeneralJson.toString(), Toast.LENGTH_SHORT).show();
                        if(job_sid==null||account_password==null||account_id==null){

                            return;
                        }

                        HttpClient.gjjTask(gjjTotal,job_sid,start_date, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                String body = null;
                                try {
                                    body = new String(responseBody, "GB2312");
                                //    if(body.contains("[]")){
                                            HttpClient.endJob(job_sid,"","2", new AsyncHttpResponseHandler() {
                                                @Override
                                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                    jobState = true;
                                                    String body = null;
                                                    try {
                                                        body = new String(responseBody, "utf-8");
                                                        System.out.println("endjob "+ "***" + body.toString());

                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                                }
                                            });
                                    //}
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                                Log.d("cja2ygjjtask",body);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });
                    }
                   // Toast.makeText(Service1.this, "catch log: " + line.getMessage(), Toast.LENGTH_SHORT).show();



                }


                // }
            }
        }.execute();
    }


    public static JSONObject stringToJson(String s) {
        System.out.println("stringToJson");
        JSONObject jo = null;
        try {
            jo = new JSONObject(s);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jo;
    }

    public static void generalGjjDataTransition(String s){
        System.out.println("robot generalGjjDataTransition "+s);
        if(s.contains("result")&&s.contains("dwbl")){
            // String[] strArray = s.split("HttpUtil");
           // System.out.println("cja2y**"+s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim());
           // String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
            System.out.println("cja2y**generalGjjDataTransition**success");
            jsonToGjjJsonGeneral(stringToJson(s.trim()));
        }else{
            System.out.println("cja2y**generalGjjDataTransition**fail");
        }

    }


    interface TransitonHandler{
        void onSuccess(String code,JSONObject generalContent,JSONObject detailContent);
    }

    public static void dataTransition(String s){

        generalGjjDataTransition(s);
        detailGjjDataTransition(s);

    }

    public static void detailGjjDataTransition(String s){
        System.out.println("robot detailGjjDataTransition "+s);
        if(s.contains("result")&&s.contains("fsje")&&s.contains("ywzy")){
            System.out.println("cja2y**detailGjjDataTransition**success");
            //  String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
            //String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
           // System.out.println("cja2y**"+s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim());
            jsonToGjjJsonDetail(stringToJson(s.trim()));
        }else{
            System.out.println("cja2y**detailGjjDataTransition**fail");
        }
    }
    public static void jsonToGjjJsonDetail(JSONObject jo) {
        ArrayList tempArray = new ArrayList();

        if (jo != null) {
            try {
                if (jo.has("result")) {
                    jo = jo.getJSONObject("result");
                    if (jo.has("detail")) {

                        JSONArray detailArrayTemp = jo.getJSONArray("detail");
                        JSONArray detailArrayTemp1 = new JSONArray();
                        for (int i = 0; i < detailArrayTemp.length(); i++) {
                            JSONArray tempArryObj = new JSONArray();
                            JSONObject currentObj = detailArrayTemp.getJSONObject(i);

                            tempArryObj.put(0,"");
                            tempArryObj.put(1,currentObj.getString("jylb"));
                            tempArryObj.put(2,currentObj.getString("jzrq"));
                            tempArryObj.put(3,currentObj.getString("ywzy"));
                            tempArryObj.put(4,"");
                            tempArryObj.put(5,currentObj.getString("fsje"));
                            tempArryObj.put(6,currentObj.getString("zhye"));

//                            tempArryObj.put(currentObj.getString("fsje"));
//                            tempArryObj.put(currentObj.getString("jcyf"));
//                           // tempArryObj.put();
//                            tempArryObj.put(currentObj.getString("jzrq"));
//                            tempArryObj.put(currentObj.getString("ywzy"));
//                            tempArryObj.put(currentObj.getString("zhye"));
                            detailArrayTemp1.put(tempArryObj);
                        }
                        detailArray = detailArrayTemp1;
                        gjjTotal.put("details",detailArray);
                        System.out.println("detailArray+" + detailArray.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static JSONObject gjjGeneralJson = null;
    public static JSONObject gjjTotal = null;
    public static JSONArray detailArray = null;

    public String account_id = null;
    public String account_password = null;
    public String job_sid = null;
    public static void jsonToGjjJsonGeneral(JSONObject jo) {
        ArrayList tempArray = new ArrayList();
        System.out.println("jsonToGjjJsonGeneral");
        if (jo != null) {
            try {
                if (jo.has("result")) {
                    jo = jo.getJSONObject("result");

                    if (jo.has("detail")) {

                        JSONArray  detailArrayTemp = jo.getJSONArray("detail");
                        JSONObject tempJson = detailArrayTemp.getJSONObject(0);
                        JSONObject tempGjjJsonChild = new JSONObject();
                       // tempGjjJsonChild.put("record_date",tempJson.getString("jzny"));
                        start_date = tempJson.getString("jzny");
                        detail_start_date = tempJson.getString("jzny");
                        tempGjjJsonChild.put("name",AES256Cipher.AES_Encode(jo.getString("zgxm"),DataConstant.aesKey));
                        tempGjjJsonChild.put("customer_id",jo.getString("grdm"));
                        tempGjjJsonChild.put("company",jo.getString("dwmc"));
                        tempGjjJsonChild.put("balance",tempJson.getString("zhye"));
                        tempGjjJsonChild.put("base",tempJson.getString("yjce"));
                        tempGjjJsonChild.put("state",tempJson.getString("zhzt"));
                        tempGjjJsonChild.put("initial_date",tempJson.getString("khrq"));
                        tempGjjJsonChild.put("deposite_base_guess","");
                        tempGjjJsonChild.put("ID",AES256Cipher.AES_Encode(jo.getString("sfzh"), DataConstant.aesKey));
                      //  JSONObject gjjJsonTemp = new JSONObject();
                     //   gjjJsonTemp.put("gjj",tempGjjJsonChild);
                        gjjGeneralJson = tempGjjJsonChild;
                        gjjTotal = tempGjjJsonChild;
                        System.out.println("gjj_json+"+gjjGeneralJson.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void  saveCapture() {
        mFloatView.setVisibility(View.INVISIBLE);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                //start virtual
                startVirtual();
            }
        }, 500);

        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                //capture the screen
                startCapture();
            }
        }, 1500);

        Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            public void run() {
                mFloatView.setVisibility(View.VISIBLE);
                //stopVirtual();
            }
        }, 1000);
    }

    private void createVirtualEnvironment() {
        dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        strDate = dateFormat.format(new java.util.Date());
        pathImage = Environment.getExternalStorageDirectory().getPath() + "/Pictures/";
        nameImage = pathImage + strDate + ".png";
        mMediaProjectionManager1 = (MediaProjectionManager) getApplication().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        mWindowManager1 = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        windowWidth = mWindowManager1.getDefaultDisplay().getWidth();
        windowHeight = mWindowManager1.getDefaultDisplay().getHeight();
        metrics = new DisplayMetrics();
        mWindowManager1.getDefaultDisplay().getMetrics(metrics);
        mScreenDensity = metrics.densityDpi;
        mImageReader = ImageReader.newInstance(windowWidth, windowHeight, 0x1, 2); //ImageFormat.RGB_565

       // Log.i(TAG, "prepared the virtual environment");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startVirtual() {
        if (mMediaProjection != null) {
           // Log.i(TAG, "want to display virtual");
            virtualDisplay();
        } else {
           // Log.i(TAG, "start screen capture intent");
         //   Log.i(TAG, "want to build mediaprojection and display virtual");
            setUpMediaProjection();
            virtualDisplay();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setUpMediaProjection() {
        mResultData = ((ShotApplication) getApplication()).getIntent();
        mResultCode = ((ShotApplication) getApplication()).getResult();
        mMediaProjectionManager1 = ((ShotApplication) getApplication()).getMediaProjectionManager();
        mMediaProjection = mMediaProjectionManager1.getMediaProjection(mResultCode, mResultData);
        //Log.i(TAG, "mMediaProjection defined");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void virtualDisplay() {
        mVirtualDisplay = mMediaProjection.createVirtualDisplay("screen-mirror",
                windowWidth, windowHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mImageReader.getSurface(), null, null);
       // Log.i(TAG, "virtual displayed");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startCapture() {
        strDate = dateFormat.format(new java.util.Date());
        nameImage = pathImage + strDate + ".png";

        Image image = mImageReader.acquireLatestImage();
        int width = image.getWidth();
        int height = image.getHeight();
        final Image.Plane[] planes = image.getPlanes();
        final ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * width;
        Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
       // bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);......................................................................................截图。。。。。。。。。。。。。全部boundsInScreen: Rect(482, 631 - 680, 709)
       bitmap = Bitmap.createBitmap(bitmap,711,923,333,130);//690,850,333,130
        image.close();
       // Log.i(TAG, "image data captured");

        if (bitmap != null) {
            try {
                File fileImage = new File(nameImage);
                if (!fileImage.exists()) {
                    fileImage.createNewFile();
                 //   Log.i(TAG, "image file created");
                }
                FileOutputStream out = new FileOutputStream(fileImage);
                if (out != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(fileImage);
                    media.setData(contentUri);
                    this.sendBroadcast(media);
                  //  Toast.makeText(this,"截屏完成，向天津服务发送消息",Toast.LENGTH_LONG).show();
                    sendSuccessMsg();
                 //   Log.i(TAG, "screen image saved");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendSuccessMsg(){
        try {
            //takeScreenShot();
            Intent intent = new Intent("com.cja2y.tjgjjrobot.MYBROADCAST");
            //  将要广播的数据添加到Intent对象中
            intent.putExtra("text", strDate);
            //  发送广播
            sendBroadcast(intent);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void tearDownMediaProjection() {
        if (mMediaProjection != null) {
            mMediaProjection.stop();
            mMediaProjection = null;
        }
       // Log.i(TAG, "mMediaProjection undefined");
    }

    private void stopVirtual() {
        if (mVirtualDisplay == null) {
            return;
        }
        mVirtualDisplay.release();
        mVirtualDisplay = null;
    //   Log.i(TAG, "virtual display stopped");
    }

    @Override
    public void onDestroy() {
        // to remove mFloatLayout from windowManager
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
        tearDownMediaProjection();
      //  Log.i(TAG, "application destroy");
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        //Log.d("harvic", msg);
      //  Toast.makeText(this, "收到天津服务信息"+msg, Toast.LENGTH_LONG).show();
        if(event.getMsg().equals("Screen shot")) {
            saveCapture();
        }else if(event.getMsg().equals("Get log")){

        }else if(event.getMsg().equals("Start task")){
          //   startTask();
            jobState =true;
        }else if(event.getMsg().equals("task failed")){

            postError();
        }
       // tv.setText(msg);

    }

    public void postError(){
        jobState=true;
        HttpClient.endJob(job_sid,"","-1", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = null;
                try {
                    body = new String(responseBody, "GB2312");
                    System.out.println("endjob "+ "***" + body.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });



    }
    public void startTask(){

        Log.d("cja2y start job", "start");
        jobState=false;
        detailArray=null;
        gjjGeneralJson = null;
        job_sid = null;
        try {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    //execute the task
                    HttpClient.getGjjTask(new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                            String body = null;
                            try {
                                body = new String(responseBody, "GB2312");
                                Log.d("cja2yresponseBody", body);

                                if (body.contains("please login first")) {
                                    loginGjj();
                                    return;
                                }

                                if (DataConstant.isDebug) {
                                    body =body.replaceAll ("\\\\r\\\\n", "");;

                                    if (body.contains("Json")) {
                                       // JSONObject jo = new JSONObject(body);
                                       // JSONObject j1 = jo.getJSONObject("Json");
                                        job_sid = "33329";
                                        account_id = "120101198101162064";
                                        account_password = "071012";
                                        sendAccountInfoToAccessbilty();
                                    } else {
                                        jobState = true;
                                    }
                                } else {
                                    if (body.contains("jobs")) {
                                        JSONObject jo = new JSONObject(body);
                                        JSONArray joArray = jo.getJSONArray("jobs");
                                        if (joArray.length() >= 1) {
                                            jobState = false;
                                            Log.d("cja2yjobget", body);
                                            JSONObject temp = joArray.getJSONObject(0);
                                            job_sid = temp.getString("sid");
                                            String temp1 = temp.getString("json");
                                            JSONObject accountJson = new JSONObject(temp1);
                                            account_id = accountJson.getString("ID");
                                            account_password = accountJson.getString("password");
                                            sendAccountInfoToAccessbilty();
                                        } else {
                                            //startTask();
                                            jobState = true;
                                        }
                                    }
                                }

                                }catch(Exception e){
                                    e.printStackTrace();
                                    //   startTask();
                                    jobState = true;
                                }




                            //   Toast.makeText(Service1.this, responseBody.toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            jobState=true;
                        }
                    });

                }
            }, 1000);
        }catch (Exception e){
           // startTask();
            jobState=false;
        }



    }

    public void sendAccountInfoToAccessbilty(){
        try {
            //takeScreenShot();
           // startService(new Intent("com.cja2y.accessibilitytest.MyAccessibilityService"));

//            Intent intent1 = new Intent();
//            intent1.setAction("com.cja2y.accessibilitytest.MyAccessibilityService");
//            intent1.setPackage("com.cja2y.accessibilitytest"); //指定启动的是那个应用（lq.cn.twoapp）中的Action(b.aidl.DownLoadService)指向的服务组件
//            bindService(intent1, null, BIND_AUTO_CREATE);
            Intent intent = new Intent("com.cja2y.tjgjjrobot.MYBROADCAST");
            //  将要广播的数据添加到Intent对象中
            intent.putExtra("text",account_id+"#"+account_password );
            //  发送广播
            sendBroadcast(intent);
        } catch (Exception e) {
            System.err.println(e);
        }


    }


    public void loginGjj(){


        HttpClient.loginGjj(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = null;
                try {
                    body = new String(responseBody, "GB2312");
                }catch (IOException e){
                    e.printStackTrace();
                }
                Log.d("cja2yxx",body);
              //  Toast.makeText(Service1.this,"success"+body , Toast.LENGTH_LONG).show();
              //  startTask();
                ToolKit.runOnMainThreadAsync(new Runnable() {
                    @Override
                    public void run() {
                       // startTimer();
                        startThread();
                    }
                });

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String body = null;
                try {
                    body = new String(responseBody, "GB2312");
                }catch (IOException e){
                    e.printStackTrace();
                }
            //    Toast.makeText(Service1.this,"failed"+body , Toast.LENGTH_LONG).show();
            }
        });
    }

    private Timer timer = null;
    private TimerTask timerTask = null;

    private void startTimer() {
        // Util.killTJAPP(this);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(jobState) {
                    timerTaskRun();
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void startThread(){
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                threadIndex++;
                if(threadIndex>70){
                    jobState = true;
                }
                System.out.println("线程池正在执行 1s 1s");
                if(jobState) {
                    threadIndex =0;
                    startTask();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }

    }

    public void timerTaskRun() {
        startTask();
    }
}
