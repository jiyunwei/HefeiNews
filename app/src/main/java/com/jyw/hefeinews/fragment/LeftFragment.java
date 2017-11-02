package com.jyw.hefeinews.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jyw.hefeinews.R;
import com.jyw.hefeinews.activity.MainActivity;
import com.jyw.hefeinews.base.BaseFragment;
import com.jyw.hefeinews.domain.NewsCenterPagerBean;
import com.jyw.hefeinews.utils.DensityUtil;
import com.jyw.hefeinews.utils.LogUtils;

import java.util.List;

/**
 * Created by pc on 2017/10/24.
 */

public class LeftFragment extends BaseFragment {
    private ListView listView;
    private List<NewsCenterPagerBean.DataBean> data;
    private int prePosition;//上一次点击的位置
    private leftMenuFragmentAdapter adapter;

    @Override
    public View initView() {
        LogUtils.e("左侧的菜单视图被初始化了");
        listView=new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context,40),0,0);
        listView.setDividerHeight(0);//设置分割线
        listView.setCacheColorHint(Color.TRANSPARENT);
        //设置按下listView item项不变色
        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prePosition=position;
                adapter.notifyDataSetChanged();//刷新适配器
                MainActivity activity = (MainActivity) LeftFragment.this.context;
                activity.getSlidingMenu().toggle();//如果侧滑开着，我就关掉，如果关掉的我就打开
            }
        });

        return listView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.e("左侧的菜单的数据被初始化了");

    }

    public void setData(List<NewsCenterPagerBean.DataBean> data) {
        this.data=data;
//        LogUtil
        for(NewsCenterPagerBean.DataBean dataBean: data){
            LogUtils.e("标题：=="+dataBean.getTitle());
        }
        adapter= new leftMenuFragmentAdapter();
        listView.setAdapter(adapter);
        LogUtils.e("listview 设置适配器了-------------");
    }

    /**
     * listView的适配器
     */
    class leftMenuFragmentAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            ViewHolder viewHolder;
            if(convertView==null){
                textView= (TextView) View.inflate(context, R.layout.item_leftmenu,null);
                viewHolder=new ViewHolder();
                viewHolder.textView=textView;


            }else{
                textView= (TextView) convertView;
            }
            textView.setText(data.get(position).getTitle());
            LogUtils.e("listview 添加数据了了-------------");

            //判断当前点击的位置，让被点击的左侧菜单项高亮显示
            textView.setEnabled(prePosition==position);

            return textView;
        }

        class ViewHolder{
            TextView textView;
        }
    }
}
