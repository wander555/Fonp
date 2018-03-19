package com.larryzhang.fonp.net;

import android.content.Context;

import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;

import java.util.List;

import rx.Observable;

import static android.R.attr.name;
import static android.R.attr.start;
import static android.R.attr.tag;

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
    public Observable<PicList> getPics(int page){
        return mRetrofitService.getPicsList(page);
    }

    public Observable<PicList> getTodayList(int page,String date){
        return mRetrofitService.getTodayList(page,date,"-fon_stat__votings");
    }
}
