package com.larryzhang.fonp.net;

import android.content.Context;

import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.bean.TranslateBean;

import rx.Observable;


/**
 * RetrofitService中定义的接口的具体实现
 * @author zhangqiang
 * @date 2018/3/19
 */


public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public DataManager(Context context,String type){
        this.mRetrofitService = RetrofitHelper.getInstance(context,type).getServer();
    }


    public Observable<PicList> getPics(int page){
        return mRetrofitService.getPicsList(page);
    }

    public Observable<PicList> getTodayList(int page,String date){
        return mRetrofitService.getTodayList(page,date,"-fon_stat__votings");
    }


    /**
     * ssr
     * @return
     */
    public Observable<SsrBean> getSSR(){
        return mRetrofitService.getSSR();
    }


    /**
     * 翻译
     * @return
     */
    public Observable<Object> getTranslate(String q,String from ,String to,String appid,String salt ,String  sign){
        return mRetrofitService.getTransInfo(q, from, to, appid, salt, sign);
    }
}
