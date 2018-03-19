package com.larryzhang.fonp.utils;

import android.widget.Toast;

import es.dmoral.toasty.Toasty;


/**
 * Author: larryzhang
 * Email: larryzhangqiang@126.com
 * Date: 2017-04-24  15:33
 */

public class ToastyUtil {

    public static void showError(String msg){
        Toasty.error(Utils.getContext(),msg, Toast.LENGTH_SHORT,true).show();
    }

    public static void showSuccess(String msg){
        Toasty.success(Utils.getContext(),msg, Toast.LENGTH_SHORT,true).show();
    }
}
