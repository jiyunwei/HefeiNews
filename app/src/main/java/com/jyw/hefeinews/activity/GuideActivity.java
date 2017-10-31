package com.jyw.hefeinews.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jyw.hefeinews.R;
import com.jyw.hefeinews.SplashActivity;
import com.jyw.hefeinews.utils.CacheUtils;
import com.jyw.hefeinews.utils.DensityUtil;

import java.util.ArrayList;



public class GuideActivity extends Activity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ArrayList<ImageView> mImagesList;
    private LinearLayout ll_point_grey;
    private ImageView ll_point_red;
    private int leftMax;
    private Button btn_start_main;
    private Bitmap bitmap;
    private static final String TAG = "GuideActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ll_point_grey = (LinearLayout) findViewById(R.id.ll_point_grey);
        ll_point_red = (ImageView) findViewById(R.id.ll_point_red);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        //给按钮添加点击事件
        btn_start_main.setOnClickListener(this);

        ll_point_red.getViewTreeObserver().addOnGlobalLayoutListener(new myGlobalListener());

        mImagesList = new ArrayList<ImageView>();
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        for (int i = 0; i < ids.length; i++) {


            ImageView image = new ImageView(this);

            image.setBackgroundResource(ids[i]);


            mImagesList.add(image);

            ImageView grey_point = new ImageView(this);
            grey_point.setBackgroundResource(R.drawable.ll_point_grey);

            int widthDpi= DensityUtil.dip2px(this,10);
            Log.d(TAG, "onCreate:-------------------- "+widthDpi);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthDpi, widthDpi);
            if (i != 0) {
                params.leftMargin = widthDpi;
            }
            grey_point.setLayoutParams(params);
            ll_point_grey.addView(grey_point);
        }


        mViewPager.setAdapter(new MyViewPagerAdapter());

        mViewPager.addOnPageChangeListener(new myViewPagerListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_main://进入主页面
                //第一：保存进入过主页面的数据
                CacheUtils.putBoolean(this, SplashActivity.START_MAIN, true);

                //第二：跳转页面
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


                //第三：结束掉当前引导页面
                finish();
                break;
        }
    }


    /**
     * 创建ViewPager监听器
     */
    class myViewPagerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int leftMargin = (int) (position * leftMax + positionOffset * leftMax);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll_point_red
                    .getLayoutParams();
            params.leftMargin = leftMargin;
            ll_point_red.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImagesList.size() - 1) {
//                AlphaAnimation aa=new AlphaAnimation(0,1);
//                aa.setDuration(2000);
//                aa.setFillAfter(true);
//                btn_start_main.startAnimation(aa);
                btn_start_main.setVisibility(View.VISIBLE);

            } else {

                btn_start_main.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    /**
     * 创建全局观察者监听器
     */

    class myGlobalListener implements ViewTreeObserver.OnGlobalLayoutListener {


        @Override
        public void onGlobalLayout() {
            ll_point_red.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            leftMax = ll_point_grey.getChildAt(1).getLeft() - ll_point_grey.getChildAt(0).getLeft();
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImagesList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImagesList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
