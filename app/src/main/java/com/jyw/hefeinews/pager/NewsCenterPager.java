package com.jyw.hefeinews.pager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jyw.hefeinews.activity.MainActivity;
import com.jyw.hefeinews.base.BasePager;
import com.jyw.hefeinews.base.MenuDatailBasePager;
import com.jyw.hefeinews.domain.NewsCenterPagerBean2;
import com.jyw.hefeinews.fragment.LeftFragment;
import com.jyw.hefeinews.menudetailpager.InteracMenuDetailPager;
import com.jyw.hefeinews.menudetailpager.NewsMenuDetailPager;
import com.jyw.hefeinews.menudetailpager.PhotosMenuDetailPager;
import com.jyw.hefeinews.menudetailpager.TopicMenuDetailPager;
import com.jyw.hefeinews.utils.CacheUtils;
import com.jyw.hefeinews.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/10/26.
 * 新闻中心
 */

public class NewsCenterPager extends BasePager {

    /**
     * 请求json返回的data数据
     */
    private List<NewsCenterPagerBean2.DataBean> data;
    /**
     * 新闻详情页面的各个子页面集合
     */
    private List<MenuDatailBasePager> datailBasePagers;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        ib_menu.setVisibility(View.VISIBLE);

        //设置点击图片使得侧滑关闭或者打开
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) NewsCenterPager.this.context;
                activity.getSlidingMenu().toggle();
            }
        });

        LogUtil.e("新闻中心数据被初始化了");
        tv_title.setText("新闻中心");
        TextView textView=new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        textView.setText("新闻中心内容");

        String dataResult = CacheUtils.getString(context,Constants.NEWSCENTER_PAGER_URL);
        if(!TextUtils.isEmpty(dataResult)){
            //如果之前有缓存数据先从缓存数据当中进行解析显示，
            // 这样即使网络无链接也能正常显示页面的内容
            processData(dataResult);
        }
        //联网请求数据
        getDataFromNet();

    }

    /**
     * 使用xUtils 联网解析数据
     */
    public void getDataFromNet() {
        RequestParams params= new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("使用xUtils联网请求成功"+result);
                //联网请求的result数据存到缓存当中
                CacheUtils.putString(context,Constants.NEWSCENTER_PAGER_URL,result);
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("使用xUtils联网请求失败"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("请求中断"+cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("请求完成");
            }
        });
    }

    /**
     * 解析json数据 显示数据
     * @param json
     */
    private void processData(String json) {
        NewsCenterPagerBean2 bean=parsedJson(json);
        data = bean.getData();
        MainActivity mainActivity = (MainActivity)context;
        LeftFragment leftFragment = mainActivity.getLeftMenuFragment();

        datailBasePagers=new ArrayList<MenuDatailBasePager>();
        datailBasePagers.add(new NewsMenuDetailPager(context));
        datailBasePagers.add(new TopicMenuDetailPager(context));
        datailBasePagers.add(new PhotosMenuDetailPager(context));
        datailBasePagers.add(new InteracMenuDetailPager(context));

        leftFragment.setData(data);
    }


    /**
     * 解析json数据   使用系统的api解析json  2.使用第三方框架 解析json  例如Gson fastJson
     * @param json
     */
    private NewsCenterPagerBean2 parsedJson(String json) {
//        Gson gson=new Gson();
//        NewsCenterPagerBean bean = gson.fromJson(json,
//                NewsCenterPagerBean.class);

        return new Gson().fromJson(json,NewsCenterPagerBean2.class);
    }

    /**
     * 通过传入左侧菜单点击产生的下标，从而告诉当前的新闻中心页面，应该填充哪些页面
     * @param position  左侧菜单栏点击之后产生的下标 默认为0
     */
    public void switchPager(int position) {
        //设置标题
        tv_title.setText(data.get(position).getTitle());
        //清除当前fl_content当中显示的内容
        fl_content.removeAllViews();

        //填充新页面进去
        MenuDatailBasePager menuDatailBasePager = datailBasePagers.get(position);
        fl_content.addView(menuDatailBasePager.rootView);
        menuDatailBasePager.initData();



    }
}
