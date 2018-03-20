package com.larryzhang.fonp.presenter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.net.DataManager;
import com.larryzhang.fonp.view.PicListView;
import com.larryzhang.fonp.view.SSRView;
import com.larryzhang.fonp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 *ssr的present
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public class SsrPresenter implements Presenter {
    private DataManager manager;

    //用来存放RxJava中的订阅关系,要及时清掉这个订阅关系，不然会发生内存泄漏
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private SSRView ssrView;
    private SsrBean ssrBean;

    public SsrPresenter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext,"ssr");
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
        ssrView = (SSRView)view;
    }


    public void getSSR(){
        mCompositeSubscription.add(manager.getSSR()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SsrBean>() {
                    @Override
                    public void onCompleted() {
                        if (ssrBean != null){
                            ssrView.onSuccess(ssrBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        ssrView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(SsrBean ssr) {
                        ssrBean = ssr;
                    }
                })
        );
    }



}
