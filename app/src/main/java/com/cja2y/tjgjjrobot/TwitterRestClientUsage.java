//package com.cja2y.tjgjjrobot;
//
//import com.loopj.android.http.JsonHttpResponseHandler;
//
//import org.apache.http.Header;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by Administrator on 2016/5/10.
// */
//public class TwitterRestClientUsage {
//    public void getPublicTimeline() throws JSONException {
//        TwitterRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // If the response is JSONObject instead of expected JSONArray
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//                // Pull out the first event on the public timeline
//                JSONObject firstEvent = timeline.get(0);
//                String tweetText = firstEvent.getString("text");
//
//                // Do something with the response
//                System.out.println(tweetText);
//            }
//        });
//    }
//
//}
