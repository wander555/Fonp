package com.larryzhang.fonp.view;

import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.bean.TranslateBean;


/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public interface TranslateView extends View {
    void onSuccess(TranslateBean translateBean);
    void onError(String result);
}
