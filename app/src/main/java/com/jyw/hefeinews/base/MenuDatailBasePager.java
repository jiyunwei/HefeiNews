package com.jyw.hefeinews.base;

import android.content.Context;
import android.view.View;

/**
 * Created by pc on 2017/11/4.
 * 菜单详情页面基类
 */

public abstract class MenuDatailBasePager {
    /**
     * 上下文
     */
    public final Context context;

    /**
     * 代表各个详情页面的视图
     */
    public View rootView;

    public MenuDatailBasePager(Context context) {
        this.context=context;
        rootView=initView();
    }

    /**
     * 抽象方法 强制孩子实现该方法，实现不同的效果
     * @return
     */
    public abstract View initView();

    /**
     * 子页面需要初始化数据 或者联网请求等时候，重写该方法
     */
    public void initData(){

    }


}
