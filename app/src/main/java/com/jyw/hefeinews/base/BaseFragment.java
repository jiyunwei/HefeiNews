package com.jyw.hefeinews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pc on 2017/10/24.
 * 基本的fragment  leftFragment 和内容fragment要继承基类
 */


public abstract class BaseFragment extends Fragment {
    public Activity context; //MainActivity

    /**
     * fragment被创建的时候回调的方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    /**
     * 当试图被创建的时候回调的方法
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 让孩子实现自己的视图，实现子类特有的效果
     * @return
     */
    public abstract View initView();

    /**
     * 当Activity被创建了之后回调的方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 如果页面上没有内容数据，则联网请求数据，并且显示到页面
     * 如果有数据则直接显示数据
     */
    public  void initData(){

    };
}
