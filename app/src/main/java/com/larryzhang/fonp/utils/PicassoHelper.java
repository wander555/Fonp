package com.larryzhang.fonp.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.larryzhang.fonp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static android.R.attr.bitmap;

/**
 * Created by Administrator on 2018/3/18.
 */

public class PicassoHelper {
    private PicassoHelper(){}

    public static void loadPaintingImage(final ImageView image, String url,String color) {
        if(!color.equals("")){
            //用一张纯色图片做占位符
            Bitmap bitmap = Bitmap.createBitmap(360, 360,
                    Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.parseColor(color));//填充颜色

            Picasso.with(Utils.getContext())
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder(new BitmapDrawable(bitmap))
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
        else{
            Picasso.with(Utils.getContext())
                    .load(url)
                    .fit()
                    .centerCrop()
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





}
