package com.larryzhang.fonp.net;

import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.bean.TranslateBean;

import java.util.List;
import java.util.Objects;

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


    /**
     * 今日最新图片
     * @param page
     * @param published_on 2018-03-11
     * @param ordering -fon_stat__votings
     */
    @Headers({"Token: h134puqMfd07LMDpM5UL", "Id:d85de2501f218682"})
    @GET("api/mobile/fon/")
    Observable<PicList> getTodayList(@Query("page") int page,@Query("published_on") String published_on,@Query("ordering") String ordering);


    /**
     * 获取随即的一个的SSR配置
     * @return
     */
    @GET("subscribeJson")
    Observable<SsrBean> getSSR();


    /**
     * 百度翻译调用
     * @param q
     * @param from
     * @param to
     * @param appid
     * @param salt
     * @param sign
     * @return
     */
    @GET("api/trans/vip/translate")
    Observable<Object> getTransInfo(@Query("q") String q, @Query("from") String from, @Query("to") String to,
                                     @Query("appid") String appid, @Query("salt") String salt, @Query("sign") String sign);

}
