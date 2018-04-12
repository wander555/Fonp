package com.larryzhang.fonp.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.utils.Utils;

/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/4/12
 */

public class PercentImageView extends android.support.v7.widget.AppCompatImageView {
    public PercentImageView(Context context) {
        super(context);
    }

    public PercentImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable d = getDrawable();

        if(d!=null){
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
