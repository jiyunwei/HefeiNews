package com.jyw.hefeinews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jyw.hefeinews.R;
import com.jyw.hefeinews.fragment.ContentFragment;
import com.jyw.hefeinews.fragment.LeftFragment;
import com.jyw.hefeinews.utils.DensityUtil;
import com.jyw.hefeinews.utils.LogUtils;

public class MainActivity extends SlidingFragmentActivity {

    public static final String CONTENT_MAIN_FRAGMENT = "content_main_Fragment";
    public static final String FL_LEFT_FRAGMENT = "fl_left_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置mainactivity窗口无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置主页面
        setContentView(R.layout.activity_main);

        //设置左侧页面
        setBehindContentView(R.layout.activity_left);

        //设置右侧的页面
        SlidingMenu slidingMenu = getSlidingMenu();
//        slidingMenu.setSecondaryMenu(R.layout.activity_right);

        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置主页占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this,200));

        //设置滑动模式  滑动边缘 全屏滑动  不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


        LogUtils.e("我执行了log");
        initFragment();


    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main_fragment,new ContentFragment(), CONTENT_MAIN_FRAGMENT);
        transaction.replace(R.id.fl_leftFragment,new LeftFragment(), FL_LEFT_FRAGMENT);
        transaction.commit();
    }

    /**
     * 获取左侧Fragment
     * @return
     */
    public LeftFragment getLeftMenuFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        LeftFragment leftFragment = (LeftFragment) fm.findFragmentByTag(FL_LEFT_FRAGMENT);
        return (LeftFragment) getSupportFragmentManager().findFragmentByTag(FL_LEFT_FRAGMENT);
    }
    /**
     * 获取内容视图ContentFragment
     * @return
     */
    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(CONTENT_MAIN_FRAGMENT);
    }
}
