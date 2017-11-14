package com.jyw.hefeinews.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jyw.hefeinews.R;
import com.jyw.hefeinews.base.MenuDatailBasePager;
import com.jyw.hefeinews.domain.NewsCenterPagerBean2;
import com.jyw.hefeinews.menudetailpager.tabdetailpager.TabDetailPager;
import com.viewpagerindicator.TabPageIndicator;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/11/4.
 * 新闻详情页面
 */

public class NewsMenuDetailPager extends MenuDatailBasePager {

    //初始化ViewPagerIndicator控件
    @ViewInject(R.id.tabPagerIndicator)
    private TabPageIndicator tabPageIndicator;

    //初始化ViewPager控件
    @ViewInject(R.id.view_pager)
    private ViewPager viewPager;
    //页签页面数据的集合
    private final List<NewsCenterPagerBean2.DataBean.DataChildren> childrens;
    private ArrayList<TabDetailPager> tabDetailPagers;

    public NewsMenuDetailPager(Context context, NewsCenterPagerBean2.DataBean dataBean) {
        super(context);
        childrens = dataBean.getChildren();
    }

    @Override
    public View initView() {

       View view=View.inflate(context, R.layout.newsmenu_detail_pager,null);

        x.view().inject(NewsMenuDetailPager.this,view);
       
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻详情页面数据被初始化了");
        //准备新闻详情页面的数据
        tabDetailPagers=new ArrayList<>();
        for(int i=0;i<childrens.size();i++){
            tabDetailPagers.add(new TabDetailPager(context,childrens.get(i)));
        }

        viewPager.setAdapter(new MyNewsMenuDetailPagerAdapter());
        tabPageIndicator.setViewPager(viewPager);


    }


    class MyNewsMenuDetailPagerAdapter extends PagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return childrens.get(position).getTitle();
        }

        /**
         * 给viewpagerIndicator加入标题
         * @return
         */


        @Override
        public int getCount() {
            return tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            tabDetailPager.initData();

            View rootView = tabDetailPager.rootView;
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
