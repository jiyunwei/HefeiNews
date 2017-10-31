package com.jyw.hefeinews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.jyw.hefeinews.base.BasePager;

import org.xutils.common.util.LogUtil;

/**
 * Created by pc on 2017/10/26.
 * 政要指南
 */

public class GovaffairPager extends BasePager {
    public GovaffairPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("政要指南数据被初始化了");
        tv_title.setText("政要指南");
        TextView textView=new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        textView.setText("政要指南内容");
    }
}
