package com.larryzhang.fonp.presenter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.net.DataManager;
import com.larryzhang.fonp.view.PicListView;
import com.larryzhang.fonp.view.View;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public class PicListPresenter implements Presenter {
    private DataManager manager;

    //用来存放RxJava中的订阅关系,要及时清掉这个订阅关系，不然会发生内存泄漏
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private PicListView picListView;
    private PicList picListBeanList;

    public PicListPresenter (Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
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
        picListView = (PicListView)view;
    }


    public void getPicList(int page){
        mCompositeSubscription.add(manager.getPics(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicList>() {
                    @Override
                    public void onCompleted() {
                        if (picListBeanList != null){
                            picListView.onSuccess(picListBeanList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        picListView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(PicList pic) {
                        picListBeanList = pic;
                    }
                })
        );
    }


    //获取今日的图片
    public void getTodayList(int page,String date){
        mCompositeSubscription.add(manager.getTodayList(page,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicList>() {
                    @Override
                    public void onCompleted() {
                        if (picListBeanList != null){
                            picListView.onSuccess(picListBeanList);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        picListView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(PicList pic) {
                        picListBeanList = pic;
                    }
                })
        );
    }

}
