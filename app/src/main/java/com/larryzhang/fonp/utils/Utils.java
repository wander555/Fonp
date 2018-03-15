package com.larryzhang.fonp.utils;

import android.content.Context;
import android.util.TypedValue;

/**工具类
 * Created by Administrator on 2018/3/14.
 */

public class Utils {

    public static int pxToDp(int px, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,
                context.getResources().getDisplayMetrics());
    }

    public static int dpToPx(float dp, Context context) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
