package com.larryzhang.fonp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.BottomActivity;
import com.larryzhang.fonp.PicDetailActivity;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.bean.TranslateBean;
import com.larryzhang.fonp.presenter.PicListPresenter;
import com.larryzhang.fonp.presenter.TransPresenter;
import com.larryzhang.fonp.utils.MD5Utils;
import com.larryzhang.fonp.utils.PicassoHelper;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.utils.Utils;
import com.larryzhang.fonp.view.PicListView;
import com.larryzhang.fonp.view.TranslateView;
import com.nex3z.flowlayout.FlowLayout;


import org.w3c.dom.Text;

import java.util.List;

import immortalz.me.library.TransitionsHeleper;

import static com.larryzhang.fonp.utils.Utils.getContext;

/**
 *  首页图片的adapter
 * @author zhangqiang
 * @date 2018/3/19
 */

public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.PicViewHolder> {
    private Context mContext;
    private List<PicListBean> data;

    //百度翻译
    private String  appid="20180326000140123";
    private String  sec_key="jhG0bUkjxG0yESrdhhpU";

    private TextView textView;

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
        holder.relativeLayout.setBackgroundColor(Color.parseColor(beauty.getColor()));

        //tag集合
//        String[] dummyTexts = beauty.getTag();
//        for (int i=0;i<dummyTexts.length;i++) {
//            if(i<3){
//                if(dummyTexts[i].length()>2){
//                    TextView textView = buildLabel(dummyTexts[i]);
//                    holder.flowLayout.addView(textView);  // Add TextView to FlowLayout
//                }
//            }
//
//       }

        PicassoHelper.loadPaintingImage(holder.beautyImage,beauty.getImg(),"");

        //点击图片跳转的方法
        //TODO
        holder.beautyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionsHeleper.startActivity((BottomActivity)mContext, PicDetailActivity.class, v,beauty.getImg());


//                ToastyUtil.showSuccess(String.valueOf(beauty.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class PicViewHolder extends RecyclerView.ViewHolder {
        ImageView beautyImage;
        RelativeLayout relativeLayout;
        FlowLayout flowLayout;

        public PicViewHolder(View itemView) {
            super(itemView);
            beautyImage = (ImageView) itemView.findViewById(R.id.image_item);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout);
            flowLayout=(FlowLayout)itemView.findViewById(R.id.flow);
        }
    }

    private TextView buildLabel(String text) {
        //初始化picListPresenter
//        transPresenter.onCreate();
//        transPresenter.attachView(translateView);
//
//        String salt = String.valueOf(System.currentTimeMillis());
//        String src = appid + "fuck" + salt + sec_key; // 加密前的原文
//        transPresenter.getTranslate("fuck","en","zh",appid,salt, MD5Utils.md5(src));

            textView = new TextView(getContext());
            textView.setText(text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setPadding(Utils.dpToPx(4, getContext()), Utils.dpToPx(4, getContext()),Utils.dpToPx(4, getContext()),Utils.dpToPx(4, getContext()));
//            textView.setBackgroundResource(R.drawable.label_bg);
            return textView;
    }

}
