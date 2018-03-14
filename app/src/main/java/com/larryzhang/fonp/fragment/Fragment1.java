package com.larryzhang.fonp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larryzhang.fonp.R;

import me.yokeyword.fragmentation.SupportFragment;

/**最新图片
 * Created by Administrator on 2018/3/13.
 */

public class Fragment1 extends Fragment {

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
//        initView(view);
        return view;
    }


}
