package com.larryzhang.fonp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larryzhang.fonp.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/12
 */

public class MainFragment extends SupportFragment {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }


}
