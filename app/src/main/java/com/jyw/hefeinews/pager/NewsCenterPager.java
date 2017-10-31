package com.jyw.hefeinews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jyw.hefeinews.activity.MainActivity;
import com.jyw.hefeinews.base.BasePager;
import com.jyw.hefeinews.domain.NewsCenterPagerBean;
import com.jyw.hefeinews.fragment.LeftFragment;
import com.jyw.hefeinews.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by pc on 2017/10/26.
 * 新闻中心
 */

public class NewsCenterPager extends BasePager {

    private List<NewsCenterPagerBean.DataBean> data;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻中心数据被初始化了");
        tv_title.setText("新闻中心");
        TextView textView=new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        textView.setText("新闻中心内容");
        //联网请求数据
        getDataFromNet();
    }

    /**
     * 使用xUtils 联网解析数据
     */
    public void getDataFromNet() {
        RequestParams params= new RequestParams(Constants.newscenter_pager_url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("使用xUtils联网请求成功"+result);
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
        NewsCenterPagerBean bean=parsedJson(json);
        data = bean.getData();
        MainActivity mainActivity = (MainActivity)context;
        LeftFragment leftFragment = mainActivity.getLeftMenuFragment();
        leftFragment.setData(data);
    }


    /**
     * 解析json数据   使用系统的api解析json  2.使用第三方框架 解析json  例如Gson fastJson
     * @param json
     */
    private NewsCenterPagerBean parsedJson(String json) {
//        Gson gson=new Gson();
//        NewsCenterPagerBean bean = gson.fromJson(json,
//                NewsCenterPagerBean.class);

        return new Gson().fromJson(json,NewsCenterPagerBean.class);
    }
}
