package com.jyw.hefeinews.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jyw.hefeinews.base.MenuDatailBasePager;

import org.xutils.common.util.LogUtil;

/**
 * Created by pc on 2017/11/4.
 * 专题详情页面
 */

public class TopicMenuDetailPager extends MenuDatailBasePager {

    private TextView textView;

    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {

       
        textView=new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
       
       
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("专题详情页面数据被初始化了");
        textView.setText("专题详情页面内容");
    }
}
