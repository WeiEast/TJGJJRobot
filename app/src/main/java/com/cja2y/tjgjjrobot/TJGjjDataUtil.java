








package com.cja2y.tjgjjrobot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/11.
 */
public class TJGjjDataUtil {
        public static JSONObject finalData = null;
        public static JSONObject originalData = null;
        public static String finalJsonString = null;

        public static String testJson1 = "{\n" +
                "    \"code\": \"0\",\n" +
                "    \"result\": {\n" +
                "        \"detail\": [\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20160418\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201604\",\n" +
                "                \"jylb\": \"G006\",\n" +
                "                \"jzrq\": \"20160412\",\n" +
                "                \"ywzy\": \"汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20160311\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201603\",\n" +
                "                \"jylb\": \"G006\",\n" +
                "                \"jzrq\": \"20160311\",\n" +
                "                \"ywzy\": \"汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2812,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20160223\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201602\",\n" +
                "                \"jylb\": \"G006\",\n" +
                "                \"jzrq\": \"20160214\",\n" +
                "                \"ywzy\": \"汇缴\",\n" +
                "                \"zhye\": 2812\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2800,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20160113\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 34\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201601\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20160107\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2834\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2800,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20151215\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 56\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201512\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20151211\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2856\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2700,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20151121\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 78\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201511\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20151104\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20151024\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201510\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20151010\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20150914\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201509\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150906\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20150827\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201508\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150811\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 22281.91,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20150717\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201507\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150708\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 22281.91\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 57.91,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"04\",\n" +
                "                \"jzrq\": \"20150701\",\n" +
                "                \"ywzy\": \"结息\",\n" +
                "                \"zhye\": 19503.91\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201506\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150603\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 19446\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201505\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150508\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 16668\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201504\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150408\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 13890\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201503\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150311\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 11112\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201502\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150212\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 8334\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201501\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20150119\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 5556\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201412\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20141208\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 2778\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 39357.27,\n" +
                "                \"jcyf\": \"\",\n" +
                "                \"jylb\": \"06\",\n" +
                "                \"jzrq\": \"20141116\",\n" +
                "                \"ywzy\": \"非销户提取\",\n" +
                "                \"zhye\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201411\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20141107\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 39357.27\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201410\",\n" +
                "                \"jylb\": \"I001\",\n" +
                "                \"jzrq\": \"20141014\",\n" +
                "                \"ywzy\": \"网上汇缴\",\n" +
                "                \"zhye\": 36579.27\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201409\",\n" +
                "                \"jylb\": \"01\",\n" +
                "                \"jzrq\": \"20140912\",\n" +
                "                \"ywzy\": \"汇缴\",\n" +
                "                \"zhye\": 33801.27\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2778,\n" +
                "                \"jcyf\": \"201408\",\n" +
                "                \"jylb\": \"01\",\n" +
                "                \"jzrq\": \"20140822\",\n" +
                "                \"ywzy\": \"汇缴\",\n" +
                "                \"zhye\": 31023.27\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 244.27,\n" +
                "                \"jcyf\": \"201203\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 28245.27\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201202\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 28001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201201\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 26001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201112\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 24001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201111\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 22001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201110\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 20001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201109\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 18001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fsje\": 2000,\n" +
                "                \"jcyf\": \"201108\",\n" +
                "                \"jylb\": \"02\",\n" +
                "                \"jzrq\": \"20140731\",\n" +
                "                \"ywzy\": \"补缴\",\n" +
                "                \"zhye\": 16001\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";


        public static String testJson2 = "{\n" +
                "    \"code\": \"0\",\n" +
                "    \"result\": {\n" +
                "        \"detail\": [\n" +
                "            {\n" +
                "                \"dwbl\": 15,\n" +
                "                \"fcrq\": \"\",\n" +
                "                \"grbl\": 15,\n" +
                "                \"jcjs\": 9260,\n" +
                "                \"jzny\": \"201604\",\n" +
                "                \"khrq\": \"20130912\",\n" +
                "                \"snjxe\": 57.91,\n" +
                "                \"snjze\": 0,\n" +
                "                \"yjce\": 2778,\n" +
                "                \"zhye\": 0,\n" +
                "                \"zhzt\": \"正常\",\n" +
                "                \"zjjcyf\": \"20160412\",\n" +
                "                \"zjjyrq\": \"20160418\",\n" +
                "                \"zjlx\": \"G\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"dwdm\": \"20511862358\",\n" +
                "        \"dwmc\": \"天津国电海运有限公司\",\n" +
                "        \"grdm\": \"082529498301\",\n" +
                "        \"khglbdm\": \"19\",\n" +
                "        \"khglbmc\": \"开发管理部\",\n" +
                "        \"khwddm\": \"01\",\n" +
                "        \"khwdmc\": \"\",\n" +
                "        \"lkkh\": \"6227000064724309256\",\n" +
                "        \"sfgjjdk\": \"1\",\n" +
                "        \"sflk\": \"0\",\n" +
                "        \"sflxjcyn\": \"0\",\n" +
                "        \"sfzh\": \"150102198002254117\",\n" +
                "        \"zgxm\": \"白嘎力\"\n" +
                "    }\n" +
                "}";

        enum TYPE {
                gjj_general,
                gjj_detail
        }


        public static JSONObject gjjGeneralJson = null;
        public static JSONObject gjjDetailJson = null;
        public static JSONArray detailArray = null;

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
                if(s.contains("HttpUtil")&&s.contains("result")&&s.contains("dwbl")){
                        // String[] strArray = s.split("HttpUtil");
                        System.out.println("cja2y**"+s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim());
                        String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
                        jsonToGjjJsonGeneral(stringToJson(str));
                }else{
                        System.out.println("cja2y**generalGjjDataTransition**fail");
                }

        }


        interface TransitonHandler{
                void onSuccess(String code,JSONObject generalContent,JSONObject detailContent);
        }

        public static void dataTransition(String s,TransitonHandler handler){

                        generalGjjDataTransition(s);
                        detailGjjDataTransition(s);
                if(gjjGeneralJson!=null||detailArray!=null){
                        handler.onSuccess("200", gjjGeneralJson,gjjDetailJson);
                }else{
                        handler.onSuccess("222",null,null);
                }
        }

        public static void detailGjjDataTransition(String s){
                if(s.contains("HttpUtil")&&s.contains("result")&&s.contains("fsje")&&s.contains("ywzy")){
                        System.out.println("cja2y**detailGjjDataTransition**success");
                        //  String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
                        String str = s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim();
                        System.out.println("cja2y**"+s.substring(s.indexOf("HttpUtil:")+9, s.length()).trim());
                        jsonToGjjJsonDetail(stringToJson(str));
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

                                                        tempArryObj.put(1,"");
                                                        tempArryObj.put(2,"jylb");
                                                        tempArryObj.put(3,"");
                                                        tempArryObj.put(4,"ywzy");
                                                        tempArryObj.put(5,"");
                                                        tempArryObj.put(6,"fsje");
                                                        tempArryObj.put(7,"zhye");
//                                                        tempArryObj.put(currentObj.getString("fsje"));
//                                                        tempArryObj.put(currentObj.getString("jcyf"));
//                                                        tempArryObj.put(currentObj.getString("jylb"));
//                                                        tempArryObj.put(currentObj.getString("jzrq"));
//                                                        tempArryObj.put(currentObj.getString("ywzy"));
//                                                        tempArryObj.put(currentObj.getString("zhye"));
                                                        detailArrayTemp1.put(tempArryObj);
                                                }
                                                detailArray = detailArrayTemp1;
                                                System.out.println(detailArray.toString());
                                        }
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }

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
                                                tempGjjJsonChild.put("record_date",tempJson.getString("jzny"));
                                                tempGjjJsonChild.put("name",jo.getString("zgxm"));
                                                tempGjjJsonChild.put("customer_id",jo.getString("grdm"));
                                                tempGjjJsonChild.put("company",jo.getString("dwmc"));
                                                tempGjjJsonChild.put("balance",tempJson.getString("zhye"));
                                                tempGjjJsonChild.put("base",tempJson.getString("yjce"));
                                                tempGjjJsonChild.put("state",tempJson.getString("zhzt"));
                                                tempGjjJsonChild.put("initial_date",tempJson.getString("khrq"));
                                                tempGjjJsonChild.put("deposite_base_guess","");
                                                tempGjjJsonChild.put("ID",jo.getString("sfzh"));
                                                JSONObject gjjJsonTemp = new JSONObject();
                                                gjjJsonTemp.put("gjj",tempGjjJsonChild);
                                                gjjGeneralJson = gjjJsonTemp;
                                                System.out.println("detailjson"+gjjGeneralJson.toString());
                                        }
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }

        public static UserInfo getUserInfo(String s){
               JSONObject jo = stringToJson(s);
                if(jo!=null){

                }

                return null;
        }
        class  UserInfo{
                public  String account = "";
                public  String password = "";
        }
}
