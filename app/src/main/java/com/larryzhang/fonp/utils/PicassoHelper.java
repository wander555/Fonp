package com.larryzhang.fonp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.larryzhang.fonp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2018/3/18.
 */

public class PicassoHelper {
    private PicassoHelper(){};

    public static void loadPaintingImage(final ImageView image, String url,String color) {
        Picasso.with(image.getContext().getApplicationContext())
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(R.color.blue)
                .into(image, new Callback() {
                @Override
                public void onSuccess() {
                    MaterialImageLoading.animate(image).setDuration(2000).start();
                }
                @Override
                public void onError() {

                }
        });
    }





}
