package com.larryzhang.fonp.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dingmouren.paletteimageview.PaletteImageView;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.path;

/**
 * 最新图片
 * Created by Administrator on 2018/3/13.
 */

public class Fragment2 extends Fragment {

    @Bind(R.id.palette)
    PaletteImageView paletteImageView;

    private String url = "https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.palette_item, container, false);
//        initView(view);
        ButterKnife.bind(this, view);
        Glide.with(Utils.getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                if (resource != null){
                    paletteImageView.setBitmap(resource);
                }
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
