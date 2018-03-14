package com.larryzhang.fonp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.refreshview.CustomRefreshView;
import com.github.mrengineer13.snackbar.SnackBar;
import com.larryzhang.fonp.MainActivity;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.duration;
import static java.lang.System.load;

/**
 * 最新图片
 * Created by Administrator on 2018/3/13.
 */

public class Fragment1 extends Fragment {

    @Bind(R.id.refresh_view)
    CustomRefreshView refreshView;

    private List<PicListBean> data;
    private BeautyAdapter adapter;
    private int pagerSize = 10;
    private int mm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
//        initView(view);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();
        adapter = new BeautyAdapter(data,getContext());

        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        refreshView.getRecyclerView().setLayoutManager(recyclerViewLayoutManager);
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
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg","#666633"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));


//                        for (int i = 0; i < pagerSize; i++) {
//                            if (mm >= 2) {
//                                data.add(String.valueOf(i));
//                            }
//                        }
//                        ++mm;
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
//                        //模拟网络出错界面
//                        if (mm == 2) {
//                            refreshView.setErrorView();
//                            refreshView.complete();
//                            return;
//                        }

                        refreshView.complete();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < pagerSize; i++) {
//                            data.add(String.valueOf(i));
//                        }
//                        if (data.size() > 20 && data.size() < 50) {
//                            refreshView.onError();
//                        } else {
//                            if (data.size() < 70) {
//                                refreshView.stopLoadingMore();
//                            }
//                        }
//                        if (data.size() >= 70) {
//                            refreshView.onNoMore();
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                }, 1000);
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        refreshView.setRefreshing(true);

        return view;
    }

    private class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.BeautyViewHolder> {

//        @Override
//        public void onBindViewHolder(final ItemViewHolder holder, final int position) {
//            holder.tv.setText("my position is " + position);
//            holder.tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    new SnackBar.Builder(getActivity())
//                            .withMessage("我是" + position + "号")
//                            .withBackgroundColorId(R.color.purple)
//                            .withDuration(SnackBar.SHORT_SNACK)
//                            .show();
//                }
//            });
//        }


        /**
         * 上下文
         */
        private Context mContext;
        /**
         * 数据集合
         */
        private List<PicListBean> data;

        public BeautyAdapter(List<PicListBean> data, Context context) {
            this.data = data;
            this.mContext = context;
        }

        @Override
        public BeautyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载item 布局文件
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new BeautyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BeautyViewHolder holder, int position) {
            //将数据设置到item上
            PicListBean beauty = data.get(position);

            new SnackBar.Builder(getActivity())
                            .withMessage(beauty.getImg())
                            .withBackgroundColorId(R.color.purple)
                            .withDuration(SnackBar.SHORT_SNACK)
                            .show();

            Picasso.with(mContext).load(beauty.getImg()).into(holder.beautyImage);

//            holder.beautyImage.setImageResource(beauty.getImg());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class BeautyViewHolder extends RecyclerView.ViewHolder {
            ImageView beautyImage;

            public BeautyViewHolder(View itemView) {
                super(itemView);
                beautyImage = (ImageView) itemView.findViewById(R.id.image_item);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
