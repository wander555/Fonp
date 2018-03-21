package com.larryzhang.fonp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.utils.PicassoHelper;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.utils.Utils;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.List;

/**
 *  首页图片的adapter
 * @author zhangqiang
 * @date 2018/3/19
 */

public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.PicViewHolder> {
    private Context mContext;
    private List<PicListBean> data;

    public PicListAdapter(List<PicListBean> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public PicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.palette_item, parent, false);
        return new PicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PicViewHolder holder, final int position) {
        //将数据设置到item上
        final PicListBean beauty = data.get(position);

        holder.beautyImage.setTagBackgroundColor(Color.parseColor("#99"+beauty.getColor().split("#")[1]));
        holder.beautyImage.setTagText(String.valueOf(beauty.getColor()));
        //网络加载图片方法
        PicassoHelper.loadPaintingImage(holder.beautyImage, beauty.getImg(), "");


        //点击图片跳转的方法
        //TODO

        holder.beautyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastyUtil.showSuccess(String.valueOf(beauty.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class PicViewHolder extends RecyclerView.ViewHolder {
        SimpleTagImageView beautyImage;

        public PicViewHolder(View itemView) {
            super(itemView);
            beautyImage = (SimpleTagImageView) itemView.findViewById(R.id.stiv);

////                //设置图片的相对于屏幕的宽高比
            int width = Utils.getContext().getResources().getDisplayMetrics().widthPixels;
            ViewGroup.LayoutParams params = beautyImage.getLayoutParams();
            params.width = width / 2-15;
            params.height = width / 2-15;
            beautyImage.setLayoutParams(params);

        }
    }
}
