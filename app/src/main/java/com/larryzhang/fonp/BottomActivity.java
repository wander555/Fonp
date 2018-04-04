package com.larryzhang.fonp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class BottomActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    JellyToolbar toolbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tab)
    PageNavigationView tab;


    NavigationController mNavigationController;
    private AppCompatEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        ButterKnife.bind(this);

        //bottombar
        mNavigationController = tab.material()
                .addItem(R.drawable.ic_restore_teal_24dp,"Recents")
                .addItem(R.drawable.ic_favorite_teal_24dp,"Favorites")
                .addItem(R.drawable.ic_nearby_teal_24dp,"Nearby")
                .build();

        viewPager.setAdapter(new TestViewPagerAdapter(getSupportFragmentManager()));
        mNavigationController.setupWithViewPager(viewPager);


        //toolbar
//        toolbar.getToolbar().setNavigationIcon(R.mipmap.ic_menu);
//        toolbar.set
        toolbar.setJellyListener(jellyListener);
        editText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.edit_text, null);
        editText.setBackgroundResource(R.color.colorTransparent);
        toolbar.setContentView(editText);
    }


    private JellyListener jellyListener = new JellyListener() {
        @Override
        public void onCancelIconClicked() {
            if (TextUtils.isEmpty(editText.getText())) {
                toolbar.collapse();
            } else {
                editText.getText().clear();
            }
        }
    };


    //下面几个类都是为了测试写的
    private class TestViewPagerAdapter extends FragmentPagerAdapter {

        public TestViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new TestFragment();
        }

        @Override
        public int getCount() {
            return mNavigationController.getItemCount();
        }
    }

    public static class TestFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.bottom_recyclerview,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setAdapter(new TestAdapter());
            recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL));
        }
    }

    private static class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int padding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 16, parent.getResources().getDisplayMetrics());
            TextView textView = new TextView(parent.getContext());
            textView.setPadding(padding,padding,padding,padding);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

            return new RecyclerView.ViewHolder(textView) {};
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if(holder.itemView instanceof TextView){
                ((TextView) holder.itemView).setText(String.valueOf(position));
            }
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }


}
