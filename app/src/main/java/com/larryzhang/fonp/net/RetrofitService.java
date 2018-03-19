package com.larryzhang.fonp.net;

import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public interface RetrofitService {


    /**
     * 查询最新图片列表，分页
     * @param page
     * @return
     */
    @Headers({"Token: h134puqMfd07LMDpM5UL", "Id:d85de2501f218682"})
    @GET("api/mobile/fon/")
    Observable<PicList> getPicsList(@Query("page") int page);

}
