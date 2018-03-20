package com.larryzhang.fonp.view;

import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.SsrBean;


/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public interface SSRView extends View {
    void onSuccess(SsrBean ssrBean);
    void onError(String result);
}
