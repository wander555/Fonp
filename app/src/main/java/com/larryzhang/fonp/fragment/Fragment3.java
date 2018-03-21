package com.larryzhang.fonp.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.SsrBean;
import com.larryzhang.fonp.presenter.PicListPresenter;
import com.larryzhang.fonp.presenter.SsrPresenter;
import com.larryzhang.fonp.utils.SSRUtils;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.utils.Utils;
import com.larryzhang.fonp.view.PicListView;
import com.larryzhang.fonp.view.SSRView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * SSR
 * Created by Administrator on 2018/3/13.
 */

public class Fragment3 extends Fragment {

    @Bind(R.id.txt_content)
    TextView txtContent;

    //初始化Presenter
    SsrPresenter ssrPresenter = new SsrPresenter(Utils.getContext());

    ClipboardManager myClipboard;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        ButterKnife.bind(this, view);

        myClipboard = (ClipboardManager) Utils.getContext().getSystemService(Utils.getContext().CLIPBOARD_SERVICE);

        ssrPresenter.onCreate();
        ssrPresenter.attachView(ssrView);
        ssrPresenter.getSSR();

        return view;
    }


    private SSRView ssrView = new SSRView() {
        @Override
        public void onSuccess(SsrBean ssrBean) {

            String ssrUrl = SSRUtils.GenerateUrl(ssrBean);
            LogUtils.e(ssrUrl);
            ClipData clipData = ClipData.newPlainText("simple text copy", ssrUrl);
            //添加ClipData对象到剪切板中
            myClipboard.setPrimaryClip(clipData);

            ToastyUtil.showSuccess("获取ssr成功！已复制到剪贴板");

        }

        @Override
        public void onError(String result) {
            ToastyUtil.showError(result);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

        ssrPresenter.onStop();
    }
}
