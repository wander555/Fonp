package com.larryzhang.fonp.net;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.larryzhang.fonp.bean.SsrBean;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public class RetrofitHelper {

    private Context mCntext;

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }


    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.goodfon.com/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }



//    用于获取ssr的地址
    public static RetrofitHelper getInstance(Context context, String type) {
//        if (instance == null){
            instance = new RetrofitHelper(context,type);
//        }
        return instance;
    }

    private RetrofitHelper(Context mContext,String type){
        mCntext = mContext;

        if(type.equals("ssr")){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://shadowsocks-share.herokuapp.com/")
                    .client(client)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        else if(type.equals("translate")){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://api.fanyi.baidu.com/")
                    .client(client)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }

    }


}
