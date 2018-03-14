package com.larryzhang.fonp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.refreshview.CustomRefreshView;
import com.fivehundredpx.greedolayout.GreedoLayoutManager;
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator;
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration;
import com.github.mrengineer13.snackbar.SnackBar;
import com.larryzhang.fonp.MainActivity;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.duration;
import static android.R.attr.width;
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
    private int pagerSize = 1;
    private int mm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();
        adapter = new BeautyAdapter(data,getContext());

        GreedoLayoutManager layoutManager = new GreedoLayoutManager(adapter);

        refreshView.getRecyclerView().setLayoutManager(layoutManager);
        refreshView.setAdapter(adapter);

        // Set the max row height in pixels
        layoutManager.setMaxRowHeight(300);

// If you would like to add spacing between items (Note, MeasUtils is in the sample project)
        int spacing = Utils.dpToPx(4, getContext());
        refreshView.getRecyclerView().addItemDecoration(new GreedoSpacingItemDecoration(spacing));

        //设置下拉圆圈的颜色
        refreshView.getSwipeRefreshLayout().setColorSchemeColors(getResources().getColor(R.color.blue));

        refreshView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();

                        //获取最新图片的方法
                        //TODO
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg","#666633"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
                        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //加载下一页图片的方法
                        //TODO
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
                            refreshView.onNoMore();
//                        }
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        refreshView.setRefreshing(true);

        return view;
    }

    private class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.BeautyViewHolder> implements GreedoLayoutSizeCalculator.SizeCalculatorDelegate {
        private Context mContext;
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
        public void onBindViewHolder(final BeautyViewHolder holder, final int position) {
            //将数据设置到item上
            final PicListBean beauty = data.get(position);

            //点击图片跳转的方法
            //TODO
            holder.beautyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SnackBar.Builder(getActivity())
                            .withMessage(String.valueOf(beauty.getId()))
                            .withBackgroundColorId(R.color.blue)
                            .withDuration(SnackBar.SHORT_SNACK)
                            .show();
                }
            });

            //网络加载图片方法
            Picasso.with(mContext).load(beauty.getImg()).into(holder.beautyImage);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public double aspectRatioForIndex(int i) {
            return 0;
        }

        class BeautyViewHolder extends RecyclerView.ViewHolder {
            ImageView beautyImage;

            public BeautyViewHolder(View itemView) {
                super(itemView);
                beautyImage = (ImageView) itemView.findViewById(R.id.image_item);

//                //设置图片的相对于屏幕的宽高比
//                int width = getContext().getResources().getDisplayMetrics().widthPixels;
//                ViewGroup.LayoutParams params = beautyImage.getLayoutParams();
//                params.width = width/2;
//                params.height =  width/2 ;
//                beautyImage.setLayoutParams(params);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
