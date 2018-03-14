package com.larryzhang.fonp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.antfortune.freeline.FreelineCore;
import com.apkfuns.logutils.LogUtils;
import com.wanjian.cockroach.Cockroach;


/**
 * @author zhangqiang
 * @date 2018/3/12
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FreelineCore.init(this);
        //打印显示异常，不crash
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LogUtils.e(throwable.toString());
//                            ToastyUtil.showError(throwable.toString());
                        } catch (Throwable e) {
                        }
                    }
                });
            }
        });




    }
}
