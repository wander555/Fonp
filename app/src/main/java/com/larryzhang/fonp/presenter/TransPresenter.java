package com.larryzhang.fonp.presenter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.bean.TranslateBean;
import com.larryzhang.fonp.net.DataManager;
import com.larryzhang.fonp.view.SSRView;
import com.larryzhang.fonp.view.TranslateView;
import com.larryzhang.fonp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/26
 */

public class TransPresenter implements Presenter {
    private DataManager manager;

    //用来存放RxJava中的订阅关系,要及时清掉这个订阅关系，不然会发生内存泄漏
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private TranslateView translateView;
    private TranslateBean translateBean;

    public TransPresenter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext,"translate");
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(View view) {
        translateView = (TranslateView) view;
    }


    public void getTranslate(String q,String from ,String to,String appid,String salt ,String  sign){
        mCompositeSubscription.add(manager.getTranslate(q, from, to, appid, salt, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        if (translateBean != null){
                            translateView.onSuccess(translateBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        translateView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Object tsb) {
                        LogUtils.e(tsb);
//                        translateBean = tsb;
                    }
                })
        );
    }

}
