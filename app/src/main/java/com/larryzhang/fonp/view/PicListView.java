package com.larryzhang.fonp.view;

import com.larryzhang.fonp.bean.PicList;


/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/19
 */

public interface  PicListView extends View {
    void onSuccess(PicList picListBeanListi);
    void onError(String result);
}
