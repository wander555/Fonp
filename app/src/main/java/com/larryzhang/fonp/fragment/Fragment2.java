package com.larryzhang.fonp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.utils.PicassoHelper;
import com.larryzhang.fonp.utils.Utils;

import net.wujingchao.android.view.SimpleTagImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Fragment2 extends Fragment {


    @Bind(R.id.stiv)
    SimpleTagImageView stiv;

    private String url = "https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.palette_item, container, false);
        ButterKnife.bind(this, view);

        PicassoHelper.loadPaintingImage(stiv,url,"");
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
