package com.larryzhang.fonp.utils;

import android.util.Base64;
import android.util.Log;

import com.larryzhang.fonp.bean.SsrBean;

import static android.content.ContentValues.TAG;

/**
 * 解析ssr，并生成传输连接
 *
 * @author zhangqiang
 * @date 2018/3/20
 */

public class SSRUtils {


    /**
     * 根据json的内容，生成ssr连接
     * @param ssrBean
     * @return
     */
    public static String GenerateUrl(SsrBean ssrBean){
//        178.62.113.160:5252:auth_sha1_v4:aes-256-ctr:http_simple:NTJzc3IuY24/?

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ssrBean.getServer()+":");
        stringBuilder.append(ssrBean.getServer_port()+":");
        stringBuilder.append(ssrBean.getSsr_protocol()+":");
        stringBuilder.append(ssrBean.getMethod()+":");
        stringBuilder.append(ssrBean.getObfs()+":");

        stringBuilder.append(makeUidToBase64(ssrBean.getPassword()));
        stringBuilder.append("/?");

        return "ssr://"+makeUidToBase64(stringBuilder.toString());
    }


    /**
     * base64加密
     * @param strUid
     * @return
     */
    public static String makeUidToBase64(String strUid){
        String enUid = new String(Base64.encode(strUid.getBytes(), Base64.DEFAULT));
        return enUid;
    }
}
