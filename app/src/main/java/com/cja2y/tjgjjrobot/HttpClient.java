package com.cja2y.tjgjjrobot;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Administrator on 2016/5/10.
 */
public class HttpClient {
    private static final String BASE_URL = DataConstant.getBaseUtl();

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
//        client.get("http://kaifa.jianbing.com/gjj/gateway.php?d=2F0YIBFkgLzH%2BTN7HJa%2BhXGawcefNRo7ndqEklHUJh9J%2Bjf%2FI64Aqm8xSHa" +
//                "fpTmXqRl700h%2FWdOb%2FnVmUa8JIbDZWHpKeub%2B9bF4l4nf1tinJzVC9%2FOaq8o5bYZi4dw5RTo%2BW0RUuThH0W2A44Yscw", responseHandler);
//        client.get("http://kaifa.jianbing.com/gjj/gateway.php?d=f")
       // "http://kaifa.jianbing.com/gjj/gatewayRobot.php?d="+"{f=login&cv=winform&passport=winform2&ov=&extra=tianjing_job&apns=&yys_cid=-1}"
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    public static void gjjTask(JSONObject totalData,String jobSid,String date, AsyncHttpResponseHandler responseHandler) {
        JSONObject param = new JSONObject();
        RequestParams params = new RequestParams();
        RequestParams paramsFinal =  new RequestParams();
        try {

           //   JSONObject gjjJsonTemp = new JSONObject();

          //  gjjJsonTemp.put("gjj",totalData);
            param.put("f", "jbReqGjj");
//            param.put("location_cid", "12");
//            param.put("args", "");
            param.put("job_sid",jobSid);
            param.put("gjj", totalData);
            param.put("start_date",date);
            param.put("detail_start_date",date);


            params.put("f","jbReqGjj");
            params.put("job_sid",jobSid);
            params.put("gjj",totalData);
            params.put("start_date",date);
            params.put("detail_start_date",date);


            paramsFinal.put("d",param);
            System.out.println("cja2y** paramsFinal** final:"+paramsFinal.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("cja2y** task url**"+DataConstant.getBaseUtl() + param.toString());
        String url = "";
        try {
            url = DataConstant.getBaseUtl() + encryptContent(param.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("cja2y** task url** final:" + url);
        //get(url, null, responseHandler);
        post(DataConstant.getBaseUtl().replace("?d=",""), paramsFinal, responseHandler);

    }


    public static void endJob(String sid,String result,String state,AsyncHttpResponseHandler responseHandler){
        JSONObject jo = new JSONObject();

        try {



            jo.put("f", "endJob");
            jo.put("sid", sid);
            jo.put("result",result);
            jo.put("state",state);


            // jo.put("extra", "tianjing_job");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("endjob**url" + DataConstant.getBaseUtl() + jo.toString());
        get(DataConstant.getBaseUtl() + jo.toString(), null, responseHandler);

    }
    public static void getGjjTask(AsyncHttpResponseHandler responseHandler) {
//        RequestParams param = new RequestParams();
//        param.put("f", "getJobs");
//        param.put("num", "1");
//        post("", param, responseHandler);


        JSONObject jo = new JSONObject();
        try {



            jo.put("f", "getJobs");
            jo.put("num", "1");
            jo.put("","");
            // jo.put("extra", "tianjing_job");

        } catch (Exception e) {
            e.printStackTrace();
        }
        //get(DataConstant.getBaseUtl() + (DataConstant.isDebug?"":jo.toString()), null, responseHandler);
        get(DataConstant.isDebug?"http://112.124.121.109:4821/AndrodService/GetJob":(DataConstant.getBaseUtl()+jo.toString()), null, responseHandler);

    }

    public static void loginGjj(AsyncHttpResponseHandler responseHandler) {
//        RequestParams param = new RequestParams();
//        param.put("f","login");
//        param.put("passport","winform2");
//        param.put("cv","");
//        param.put("ov","");
//        param.put("apns","");
//        param.put("yys_cid","");
//        param.put("extra","tianjing_job");
//        RequestParams params = new RequestParams();
//        params.put("d",param);
        JSONObject jo = new JSONObject();
        try {
            jo.put("f", "login");
            jo.put("passport", "winform2");
            jo.put("cv", "winform");

            jo.put("yys_cid", "-1");
           // jo.put("extra", "tianjing_job");

        } catch (Exception e) {

            e.printStackTrace();
        }

     //   String url = generateGetRequest(jo);
        Log.d("cja2y url**",DataConstant.getBaseUtl()+jo.toString());
        get(DataConstant.getBaseUtl() + jo.toString(), null, responseHandler);
    }


    static public String generateGetRequest(JSONObject jsonObject) {
        String url = BASE_URL;

        String str = jsonObject.toString();

        try {
            url = url + encryptContent(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    static private String Base64Content(String content) throws Exception {
        return URLEncoder.encode(Base64.encodeToString(content.getBytes(), Base64.NO_PADDING | Base64.NO_WRAP));
    }

    static private String encryptContent(String content) throws Exception {
        return URLEncoder.encode(content, "utf-8");
    }

    static public void tjAppLogin(final Context context){

       // String s2 = vercodeEditText.getText().toString();
        HashMap hashmap = new HashMap();
        hashmap.put("user", "120102196301250111");
        hashmap.put("pass", "125811");
        hashmap.put("vercode", "abcd");
        hashmap.put("yz", "0");
        hashmap.put("appType", Integer.valueOf(1));

        RequestParams param = new RequestParams();
        param.put("user", "120102196301250111");
        param.put("pass", "125811");
        param.put("vercode", "x3eh");
        param.put("yz", "0");
        param.put("appType", Integer.valueOf(1));
      //  return new JSONObject(HttpUtil.postRequest("http://app.zfgjj.cn/appserver/app/cipherlogin.app", hashmap));
        client.get("http://app.zfgjj.cn/appserver/app/cipherlogin.app", param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = null;
                try {
                    body = new String(responseBody, "GB2312");
                }catch (IOException e){
                    e.printStackTrace();
                }
                Log.d("cja2yxx",body);
                 Toast.makeText(context, "cja2yxx" + body, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
