package com.jyw.hefeinews.menudetailpager.tabdetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jyw.hefeinews.base.MenuDatailBasePager;
import com.jyw.hefeinews.domain.NewsCenterPagerBean2;

/**
 * Created by Administrator on 2017/11/14.
 * 页签详情页面
 */

public class TabDetailPager extends MenuDatailBasePager {

    private final NewsCenterPagerBean2.DataBean.DataChildren dataChildren;
    private TextView textView;

    public TabDetailPager(Context context, NewsCenterPagerBean2.DataBean.DataChildren dataChildren) {
        super(context);
        this.dataChildren=dataChildren;

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
        textView.setText(dataChildren.getTitle());
    }
}
