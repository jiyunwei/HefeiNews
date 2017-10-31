package com.jyw.hefeinews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.jyw.hefeinews.base.BasePager;

import org.xutils.common.util.LogUtil;

/**
 * Created by pc on 2017/10/26.
 * 设置中心
 */

public class SettingPager extends BasePager {
    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("设置中心数据被初始化了");
        tv_title.setText("设置中心");
        TextView textView=new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        textView.setText("设置中心内容");
    }
}
