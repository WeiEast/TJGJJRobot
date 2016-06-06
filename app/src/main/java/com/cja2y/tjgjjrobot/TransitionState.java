package com.cja2y.tjgjjrobot;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TransitionState {
    public static int detailType = 0;
    public static boolean startTranstionState = false;
    public TransitionState(){
        detailType = 0;
        startTranstionState = false;
    }
    public static void  beginState(){
        startTranstionState = true;
    }
    public static void runState(){

        if(startTranstionState) {
            detailType++;
        }
    }
    public static void cleanState(){
        detailType = 0;
        startTranstionState = true;
    }
}
