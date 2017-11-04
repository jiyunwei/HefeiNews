package com.jyw.hefeinews.fragment;

import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jyw.hefeinews.R;
import com.jyw.hefeinews.activity.MainActivity;
import com.jyw.hefeinews.base.BaseFragment;
import com.jyw.hefeinews.base.BasePager;
import com.jyw.hefeinews.pager.GovaffairPager;
import com.jyw.hefeinews.pager.HomePager;
import com.jyw.hefeinews.pager.NewsCenterPager;
import com.jyw.hefeinews.pager.SettingPager;
import com.jyw.hefeinews.pager.SmartServicePager;
import com.jyw.hefeinews.view.NoScrollViewpager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by pc on 2017/10/25.
 */

public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.viewpager)
    private NoScrollViewpager viewPager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;
    /**
     * 装5个页面的集合
     */
    private ArrayList<BasePager> basePagers;

    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.content_fragment,null);
//        使用xUtils注释来实现了控件的初始化工作
//        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
//        rg_main= (RadioGroup) view.findViewById(R.id.rg_main);
        x.view().inject(this,view); //将当前类和我的view视图关联
        return view;
    }



    @Override
    public void initData() {
        super.initData();
        basePagers=new ArrayList<BasePager>();
        /**
         * 初始化5个页面，并且放入集合当中
         */
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsCenterPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new GovaffairPager(context));
        basePagers.add(new SettingPager(context));



        rg_main.setOnCheckedChangeListener(new myOnCheckedChengeListener());

        //设置viewpager的适配器
        viewPager.setAdapter(new ContentFragmentAdapter());
        viewPager.addOnPageChangeListener(new myOnPageChangeListener());


        //默认让导航当中的主页默认选中
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();

        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);

    }

    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) basePagers.get(1);
    }

    /**
     * viewPager 监听器  主要用于监听页面发生改变时加载数据 获取数据到页面
     */
    class myOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * radioGroup 的适配器
     */
    class myOnCheckedChengeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId){
                case R.id.rb_home://主页
                    viewPager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);

                    break;
                case R.id.rb_newscenter://新闻中心
                    viewPager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://智慧服务
                    viewPager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair:// 政要指南
                    viewPager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:// 设置中心
                    viewPager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }

    }

    //是否允许侧滑
    private void isEnableSlidingMenu(int touchMode) {
        MainActivity activity = (MainActivity) context;
        activity.getSlidingMenu().setTouchModeAbove(touchMode);
    }

    /**
     * viewpager的适配器
     */
    class ContentFragmentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = basePagers.get(position); //得到的各个页面的实例
            View rootView=basePager.rootView;
            //调用各个页面的initData()方法
//            basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
