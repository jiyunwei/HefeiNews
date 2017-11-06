package com.jyw.hefeinews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jyw.hefeinews.SplashActivity;

/**
 * Created by pc on 2017/10/20.
 */

public class CacheUtils {
    //通过判断sharedPreferences当中保存的boolean值来判断用户有没有进入过引导页面
    public static  boolean getBoolean(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(key,Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }
    //进入过引导页面保存数据
    public static void putBoolean(Context  context, String key, boolean value) {
        SharedPreferences sp=context.getSharedPreferences(key,context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    //联网成功之后，把得到的json数据存储到缓存中
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp=context.getSharedPreferences(SplashActivity.START_MAIN,context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    //拿到之前联网时缓存的数据
    public static String getString(Context context, String key) {

        SharedPreferences sp=context.getSharedPreferences(SplashActivity.START_MAIN,context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
