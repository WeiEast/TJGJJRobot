package com.cja2y.tjgjjrobot;

/**
 * Created by Administrator on 2016/5/10.
 */
public class DataConstant {
    public final static Boolean isDebug = false;

    public final static String aesKey = "zt2i6cea0fceabb063e3961gg67op345";
    public static String getBaseUtl(){
        return  isDebug?"http://kaifa.jianbing.com/gjj/gatewayRobot.php?d=":"http://b.jianbing.com/gjj/gatewayRobot.php?d=";
    }

}
