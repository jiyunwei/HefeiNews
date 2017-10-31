package com.jyw.hefeinews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jyw.hefeinews.base.BaseFragment;
import com.jyw.hefeinews.domain.NewsCenterPagerBean;
import com.jyw.hefeinews.utils.LogUtils;

import java.util.List;

/**
 * Created by pc on 2017/10/24.
 */

public class LeftFragment extends BaseFragment {
    private TextView textView;
    private List<NewsCenterPagerBean.DataBean> data;

    @Override
    public View initView() {
        LogUtils.e("左侧的菜单视图被初始化了");
        textView=new TextView(context);
        textView.setTextSize(23);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.e("左侧的菜单的数据被初始化了");
        textView.setText("左侧的菜单页面");
    }

    public void setData(List<NewsCenterPagerBean.DataBean> data) {
        this.data=data;
//        LogUtil
    }
}
