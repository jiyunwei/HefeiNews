package com.jyw.hefeinews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by pc on 2017/10/27.
 */

public class NoScrollViewpager extends ViewPager {
    public NoScrollViewpager(Context context) {
        super(context);
    }

    public NoScrollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 处理Viewpager的滑动事件
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
