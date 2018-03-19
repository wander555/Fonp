package com.larryzhang.fonp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.refreshview.CustomRefreshView;
import com.larryzhang.fonp.MainActivity;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.adapter.PicListAdapter;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.presenter.PicListPresenter;
import com.larryzhang.fonp.utils.PicassoHelper;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.view.PicListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.duration;
import static android.R.attr.width;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.lang.System.load;

/**
 * 最新图片
 * Created by Administrator on 2018/3/13.
 */

public class Fragment1 extends Fragment {

    @Bind(R.id.refresh_view)
    CustomRefreshView refreshView;

    private List<PicListBean> data;
    private PicListAdapter adapter;
    private int pagerSize = 1;
    private int mm;

    //初始化Presenter
    PicListPresenter picListPresenter = new PicListPresenter(getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();

        //初始化picListPresenter
        picListPresenter.onCreate();

        picListPresenter.attachView(picListView);

        adapter = new PicListAdapter(data,getActivity());
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        refreshView.getRecyclerView().setLayoutManager(layoutManager);
        refreshView.setAdapter(adapter);


        //设置下拉圆圈的颜色
        refreshView.getSwipeRefreshLayout().setColorSchemeColors(getResources().getColor(R.color.blue));

        refreshView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        pagerSize=1;
                        //获取最新图片的方法
                        picListPresenter.getPicList(pagerSize);
//                        //模拟无数据界面
//                        if (mm == 1) {
//                            TextView textView = new TextView(getContext());
//                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
//                            textView.setLayoutParams(params);
//                            textView.setGravity(Gravity.CENTER);
//                            textView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    refreshView.setRefreshing(true);
//                                }
//                            });
//
//                            textView.setText("自定义的无数据界面");
//                            refreshView.setCreateView(textView);
//                            refreshView.setEmptyView("暂无数据");
//                            refreshView.complete();
//                            return;
//                        }
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pagerSize++;
                        //加载下一页图片的方法
                        picListPresenter.getPicList(pagerSize);
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        refreshView.setRefreshing(true);
        return view;
    }


    private PicListView picListView = new PicListView() {
        @Override
        public void onSuccess(PicList pic) {
            data.addAll(pic.getResults());
            refreshView.complete();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {
            ToastyUtil.showError(result);
            mm=2;
            refreshView.setErrorView();
            refreshView.complete();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

        picListPresenter.onStop();

    }
}
