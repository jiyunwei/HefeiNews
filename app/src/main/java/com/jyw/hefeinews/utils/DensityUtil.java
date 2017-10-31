package com.jyw.hefeinews.utils;

import android.content.Context;

/**
 * Created by pc on 2017/10/23.
 */

/**获取手机分辨率进行转换从dip转换成px
 * Created by Administrator on 2017/10/22 0022.
 */
public class DensityUtil {
    /*
* 根据手机的分辨率从dip的单位转换成px(像素)
*
* */
    public static int dip2px(Context context, float dpValue){
        //获取屏幕分辨率（密度）
        final  float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5f);
    }
    public static  int px2dip(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;

        return (int) (pxValue/scale+0.5f);
    }

}
