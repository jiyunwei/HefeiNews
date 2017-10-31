package com.jyw.hefeinews.utils;

import android.content.Context;
import android.content.SharedPreferences;

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
}
