package com.jyw.hefeinews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jyw.hefeinews.R;

/**
 * Created by pc on 2017/10/26.
 */

public class BasePager {
    public final Context context;
    public View rootView;
    /**
     * 显示标题
     */
    public TextView tv_title;

    /**
     * 点击侧滑
     */
    public ImageButton ib_menu;
    /**
     * 帧布局显示内容
     */
    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;
        this.rootView = initView();
    }

    /**
     * 用于公共部分的视图，并且初始化加载子视图的FrameLayout
     *
     * @return
     */
    private View initView() {
        /**
         * 基类的布局
         */
        View view = View.inflate(context, R.layout.base_pager, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);

        return view;
    }

    /**
     * 初始化数据：当孩子需要初始化数据 或者绑定数据 联网请求数据等请求时调用此方法
     */
    public void initData() {

    }
}
