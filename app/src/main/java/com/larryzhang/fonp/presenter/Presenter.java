package com.larryzhang.fonp.presenter;

import android.content.Intent;

import com.larryzhang.fonp.view.View;

/**
 * presenter主要用于网络的请求以及数据的获取
 * @author zhangqiang
 * @date 2018/3/19
 */

public interface Presenter {
    void onCreate();

    void onStop();


    //用于绑定我们定义的View
    // 你想把请求下来的数据实体类给哪个View就传入哪个View
    void attachView(View view);

}
