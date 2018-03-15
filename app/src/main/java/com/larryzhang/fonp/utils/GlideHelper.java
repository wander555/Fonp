package com.larryzhang.fonp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 图片加加载工具
 * 2018年3月15日14:11:27
 */
public class GlideHelper {

    private GlideHelper() {}

    public static void loadPaintingImage(ImageView image, String url) {
        Glide.with(image.getContext().getApplicationContext())
                .load(url)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }

}
