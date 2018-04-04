package com.larryzhang.fonp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.larryzhang.fonp.fragment.BaseExampleFragment;
import com.larryzhang.fonp.fragment.ScrollingSearchExampleFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements BaseExampleFragment.BaseExampleFragmentCallbacks, NavigationView.OnNavigationItemSelectedListener{

    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(this);

        showFragment(new ScrollingSearchExampleFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
//            case R.id.sliding_list_example:
//                showFragment(new SlidingSearchResultsExampleFragment());
//                return true;
//            case R.id.sliding_search_bar_example:
//                showFragment(new SlidingSearchViewExampleFragment());
//                return true;
//            case R.id.scrolling_search_bar_example:
//                showFragment(new ScrollingSearchExampleFragment());
//                return true;
            default:
                return true;
        }

    }


    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);

    }

    @Override
    public void onBackPressed() {
        List fragments = getSupportFragmentManager().getFragments();
        BaseExampleFragment currentFragment = (BaseExampleFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }
}
