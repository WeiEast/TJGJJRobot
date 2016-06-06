//package com.cja2y.tjgjjrobot;
//
//import android.accessibilityservice.AccessibilityService;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by Administrator on 2016/4/7.
// */
//public class Service2 extends AccessibilityService {
//
//    private Dama2Web dama2 = new Dama2Web(205, "9503ce045ad14d83ea876ab578bd3184", "jianghejun2002", "1225pass");//e9cd57222f08a8012b03f9163a7177cb//9503ce045ad14d83ea876ab578bd3184
//    //��֤��id
//    private int id;
//    //sd����Ŀ¼
//    private String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//    String[] PACKAGES = {"com.android.settings"};
//    private AccessibilityEvent currentEvent;
//    private AccessibilityNodeInfo editText1, editText2, editText3, registerBtn, loginBtn;
//
//    private Timer timer = null;
//    private TimerTask timerTask = null;
//
//    private static final String TAG = "Accessibility";
//
//    private void startTimer() {
//
//        timer = new Timer();
//        timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                timerTaskRun();
//            }
//        };
//        timer.schedule(timerTask, 0, 30000);
//    }
//
//    public void stopTimer() {
//        if (timer != null) {
//            timer.cancel();
//            timer.purge();
//            timer = null;
//        }
//        if (timerTask != null) {
//            timerTask.cancel();
//            timerTask = null;
//        }
//
//    }
//
//    public void timerTaskRun() {
//        performClick();
//    }
//
//    private void performClick() {
//        //
//
//        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
//
//        if (nodeInfo == null) {
//            Log.w(TAG, "rootWindow为空");
//            return;
//        }
//
//
////        AccessibilityNodeInfo currentParent = list.get(0).getParent();
////        AccessibilityNodeInfo currentParent = nodeInfo.getChild(0);
////        if(currentParent!=null){
////            currentParent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////        }
//
//        //nodeInfo = nodeInfo.getChild(0);
//        //  List<AccessibilityNodeInfo> editText1 = nodeInfo.findAccessibilityNodeInfosByText("身份证号");
//        // AccessibilityNodeInfo editText1 = findNodeInfosByText(nodeInfo.getChild(2),"身份证号");
//        if (nodeInfo.getChild(2) == null) {
//            return;
//        }
//        if (nodeInfo.getChild(2).getChildCount() < 12) {
//            return;
//        }
//        editText1 = nodeInfo.getChild(2).getChild(3);
//
//        editText2 = nodeInfo.getChild(2).getChild(6);
//        editText3 = nodeInfo.getChild(2).getChild(9);
//        registerBtn = nodeInfo.getChild(2).getChild(11);
//        loginBtn = nodeInfo.getChild(2).getChild(12);
//        for (int i = 0; i < nodeInfo.getChild(2).getChildCount(); i++) {
//            //  Log.i(TAG,nodeInfo.getChild(2).getChild(i).getClassName()+"");
//        }
//        if (editText1 == null || editText2 == null || editText3 == null) {
//            // Toast.makeText(getApplication(),"null"+nodeInfo.getChild(1).toString(),Toast.LENGTH_LONG).show();
//            return;
//        }
//
////        if(editText1.getClassName().equals("android.widget.EditText")){
////            Toast.makeText(getApplication(),"success",Toast.LENGTH_LONG).show();
////            Bundle arguments = new Bundle();
////            arguments.putCharSequence(AccessibilityNodeInfo
////                    .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "android");
////            editText1.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
////        }
//        editTextAction(editText1, "120225197912060813");
//        editTextAction(editText2, "206208");
//        editTextAction(editText3, "333");
//        //loginBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//        new getDataAsyk().execute("decodeUrl");
//    }
//
//    private void editTextAction(AccessibilityNodeInfo nodeInfo, String text) {
//        // nodeInfo.setFocused(true);
//        Bundle arguments = new Bundle();
//        arguments.putCharSequence(AccessibilityNodeInfo
//                .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
//        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//    }
//
//    class getDataAsyk extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            String msg = params[0];
//            String s = null;
//            if ("queryBalance".equals(msg)) {
//                Dama2Web.ReadBalanceResult res = dama2.getBalance();
//                if (res.ret >= 0) {
//                    s = "balance=" + res.balance;
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//            }
//            if ("readInfo".equals(msg)) {
//                Dama2Web.ReadInfoResult res = dama2.readInfo();
//                if (res.ret == 0) {
//                    s = "name=" + res.name + "; qq=" + res.qq + "; email=" + res.email + "; tel=" + res.tel;
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//            }
//            if ("decodeUrl".equals(msg)) {
//                int type = 42;
//                int timeout = 30;
//                String url = "http://icode.renren.com/getcode.do?t=web_reg&rnd=1383107243557";
//                Dama2Web.DecodeResult res = dama2.decodeUrlAndGetResult(url, type, timeout);
//                if (res.ret >= 0) {
//                    id = res.ret;
//                    //s = "success: result=" + res.result + "; id=" + res.ret;
//                    s = res.result;
//
//
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//            }
//            if ("decode".equals(msg)) {
//                int type = 200;
//                int timeout = 30;
//                //��ȡsd����Ŀ¼��getcode.jpgͼƬ
//                File file = new File(sdPath + "/getcode.jpg");
//                if (!file.exists()) {
//                    return "sd����Ŀ¼ȱ��getcode.jpgͼƬ";
//                }
//
//                FileInputStream fis;
//                byte[] data = new byte[(int) file.length()];
//                try {
//                    fis = new FileInputStream(file);
//                    fis.read(data);
//                    fis.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Dama2Web.DecodeResult res = dama2.decodeAndGetResult(type, timeout, data);
//                if (res.ret >= 0) {
//                    id = res.ret;
//                    s = "success: result=" + res.result + "; id=" + res.ret;
////                    editTextAction(editText3, res.result);
////                    loginBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//
//            }
//            if ("decodeText".equals(msg)) {
//                int type = 200;
//                int timeout = 30;
//
//                Dama2Web.DecodeResult res = dama2.decodeAndGetResult(type, timeout, "һ���϶�");
//                if (res.ret >= 0) {
//                    id = res.ret;
//                    s = "success: result=" + res.result + "; id=" + res.ret;
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//            }
//            if ("reportError".equals(msg)) {
//                Dama2Web.RequestResult res = dama2.reportError(id);
//                if (res.ret == 0) {
//                    s = "report success(id=" + id + ")";
//                } else {
//                    s = "failed: ret = " + res.ret + "; desc=" + res.desc;
//                }
//            }
//            return s;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            editTextAction(editText3, result);
//            loginBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            saveCaptcha();
//        }
//    }
//
//    private void saveCaptcha(){
//
//
//
//    }
//
//    public void onServiceConnected() {
//        //mPanel = new Panel(getApplicationContext());
//        //  startTimer();
//
//        startTimer();
//
//
//        // currentActivity =
//        //performClick();
//    }
//
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//        // TODO Auto-generated method stub
//        int eventType = event.getEventType();
//        //saveCaptcha();
//        currentEvent = event;
//
//        String eventText = "";
//        Log.i(TAG, "==============Start====================");
//        switch (eventType) {
//            case AccessibilityEvent.TYPE_VIEW_CLICKED:
//                Log.i(TAG, "==============Start====================");
//                eventText = "TYPE_VIEW_CLICKED";
//                AccessibilityNodeInfo noteInfo = event.getSource();
//                Log.i(TAG, noteInfo.toString());
//                Log.i(TAG, "=============END=====================");
//                break;
//            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
//                eventText = "TYPE_VIEW_FOCUSED";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
//                eventText = "TYPE_VIEW_LONG_CLICKED";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_SELECTED:
//                eventText = "TYPE_VIEW_SELECTED";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
//                eventText = "TYPE_VIEW_TEXT_CHANGED";
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
//
//                // Toast.makeText(getApplication(), event.getClassName().toString(), Toast.LENGTH_LONG).show();
//
//                eventText = "TYPE_WINDOW_STATE_CHANGED";
//                break;
//            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                eventText = "TYPE_NOTIFICATION_STATE_CHANGED";
//                break;
//            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
//                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
//                break;
//            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
//                eventText = "TYPE_ANNOUNCEMENT";
//                break;
//            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
//                eventText = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
//                eventText = "TYPE_VIEW_HOVER_ENTER";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
//                eventText = "TYPE_VIEW_HOVER_EXIT";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
//                eventText = "TYPE_VIEW_SCROLLED";
//                break;
//            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
//                eventText = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
//                eventText = "TYPE_WINDOW_CONTENT_CHANGED";
//                break;
//        }
//        eventText = eventText + ":" + eventType;
////        if(event.getSource().getClassName().equals("android.widget.EditText")) {
////            //event.getSource().setText("成功");
////            Toast.makeText(getApplicationContext(),"success"+event.getClassName()+"****"+event.getSource().getClassName(),Toast.LENGTH_LONG).show();
////            Bundle arguments = new Bundle();
////           arguments.putCharSequence(AccessibilityNodeInfo
////                    .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "android");
////           event.getSource().performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
////        }
//        // performClick();
//
//        Log.i(TAG, eventText);
//        Log.i(TAG, "=============END=====================");
//    }
//
//    public void onInterrupt() {
//
//    }
//}
