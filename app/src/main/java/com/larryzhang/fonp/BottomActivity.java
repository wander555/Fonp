package com.larryzhang.fonp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.gyf.barlibrary.ImmersionBar;
import com.larryzhang.fonp.fragment.BaseExampleFragment;
import com.larryzhang.fonp.fragment.FolderFragment;
import com.larryzhang.fonp.fragment.Fragment1;
import com.larryzhang.fonp.fragment.Fragment2;
import com.larryzhang.fonp.fragment.Fragment3;
import com.larryzhang.fonp.fragment.ScrollingSearchExampleFragment;
import com.larryzhang.fonp.utils.BackHandlerHelper;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BottomActivity extends AppCompatActivity implements BaseExampleFragment.BaseExampleFragmentCallbacks {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @Bind(R.id.container)
    LinearLayout container;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    private ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        ButterKnife.bind(this);

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性


        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("New", ContextCompat.getColor(this, R.color.purple), R.drawable.ic_dashboard_black_24dp);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Top", ContextCompat.getColor(this, R.color.blue), R.drawable.ic_menu_gallery);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Rnd", ContextCompat.getColor(this, R.color.white), R.drawable.ic_menu_camera);

        bottomNavigation.addTab(bottomNavigationItem1);
        bottomNavigation.addTab(bottomNavigationItem2);
        bottomNavigation.addTab(bottomNavigationItem3);

        bottomNavigation.willNotRecreate(true);
        bottomNavigation.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {

                switchPages(index);
            }
        });


        initFragments();
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
            fragmentTransaction.add(R.id.content, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    //初始化fragment，有多少加多少
    private void initFragments() {
            Fragment1 fragment1 = new Fragment1();  //最新
            Fragment2 fragment2 = new Fragment2();     //random
            FolderFragment fragment0 = new FolderFragment(); //今日top

            Fragment3 fragment3 = new Fragment3();     //ssr

            ScrollingSearchExampleFragment searchFragment =  new ScrollingSearchExampleFragment(); //带搜索框的fragment

            mFragments.add(searchFragment);
            mFragments.add(fragment0);
            mFragments.add(fragment2);
    }


    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

}
