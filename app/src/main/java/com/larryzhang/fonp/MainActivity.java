package com.larryzhang.fonp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.fragment.FolderFragment;
import com.larryzhang.fonp.fragment.Fragment1;
import com.larryzhang.fonp.fragment.Fragment2;
import com.larryzhang.fonp.fragment.Fragment3;
import com.larryzhang.fonp.ui.ext.ScaleTransitionPagerTitleView;
import com.larryzhang.fonp.utils.BackHandlerHelper;
import com.larryzhang.fonp.utils.GlideHelper;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    private static final String[] CHANNELS = new String[]{"NEW", "TOP10", "RANDOM"};
    @Bind(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragments();
        initMagicIndicator1();

        mFragmentContainerHelper.handlePageSelected(0, false);
        switchPages(0);
    }


    //fragment点击跳转
    private void switchPages(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        for (int i = 0, j = mFragments.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mFragments.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mFragments.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    //初始化fragment，有多少加多少
    private void initFragments() {
        for (int i = 0; i < CHANNELS.length; i++) {
            Fragment1 fragment1 = new Fragment1();
            Fragment2 fragment2 = new Fragment2();
            FolderFragment fragment0 = new FolderFragment();

//            Bundle bundle = new Bundle();
//            bundle.putString(Fragment1.EXTRA_TEXT, CHANNELS[i]);
//            testFragment.setArguments(bundle);
            mFragments.add(fragment1);
            mFragments.add(fragment0);
            mFragments.add(fragment2);
        }
    }


    private void initMagicIndicator1() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(CHANNELS[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        switchPages(index);
                    }
                });

//                simplePagerTitleView.setBackgroundColor(getResources().getColor(R.color.blue));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
                return indicator;
            }
            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.2f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }


    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

}
