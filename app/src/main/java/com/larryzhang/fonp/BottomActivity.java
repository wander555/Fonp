package com.larryzhang.fonp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.larryzhang.fonp.fragment.BaseExampleFragment;
import com.larryzhang.fonp.fragment.ScrollingSearchExampleFragment;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BottomActivity extends AppCompatActivity implements BaseExampleFragment.BaseExampleFragmentCallbacks {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @Bind(R.id.container)
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        ButterKnife.bind(this);

        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("New", ContextCompat.getColor(this, R.color.purple), R.drawable.ic_dashboard_black_24dp);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Top", ContextCompat.getColor(this, R.color.blue), R.drawable.ic_menu_gallery);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Fav", ContextCompat.getColor(this, R.color.white), R.drawable.ic_menu_camera);

        bottomNavigation.addTab(bottomNavigationItem1);
        bottomNavigation.addTab(bottomNavigationItem2);
        bottomNavigation.addTab(bottomNavigationItem3);

        bottomNavigation.willNotRecreate(true);
        bottomNavigation.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                ToastyUtil.showSuccess("" + index);
            }
        });


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new ScrollingSearchExampleFragment()).commit();
    }


    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
    }
}
