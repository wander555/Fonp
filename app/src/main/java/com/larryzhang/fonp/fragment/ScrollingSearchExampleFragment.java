package com.larryzhang.fonp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.example.refreshview.CustomRefreshView;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.adapter.KeyDataHelper;
import com.larryzhang.fonp.adapter.KeySuggestion;
import com.larryzhang.fonp.adapter.PicListAdapter;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.presenter.PicListPresenter;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.view.PicListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ScrollingSearchExampleFragment extends BaseExampleFragment implements AppBarLayout.OnOffsetChangedListener {

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private FloatingSearchView mSearchView;
    private AppBarLayout mAppBar;
    private CustomRefreshView refreshView;

    private boolean mIsDarkSearchTheme = false;
    private String mLastQuery = "";


    //下拉刷新页面
    private List<PicListBean> data;
    private PicListAdapter adapter;
    private int pagerSize = 1;

    //初始化Presenter
    PicListPresenter picListPresenter = new PicListPresenter(getContext());


    public ScrollingSearchExampleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling_search_example, container, false);
}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mAppBar = (AppBarLayout) view.findViewById(R.id.appbar);
        refreshView = (CustomRefreshView)view.findViewById(R.id.refresh_view);
        mAppBar.addOnOffsetChangedListener(this);

        setupDrawer();
        setupSearchBar();

        setupRecycleView();
    }


    private void setupRecycleView(){
        data = new ArrayList<>();

        //初始化picListPresenter
        picListPresenter.onCreate();

        picListPresenter.attachView(picListView);

        adapter = new PicListAdapter(data,getActivity());
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);


        refreshView.getRecyclerView().setBackgroundColor(Color.parseColor("#E9E9E9"));
        refreshView.getRecyclerView().setLayoutManager(layoutManager);
        refreshView.setAdapter(adapter);

        //设置下拉圆圈的颜色
        refreshView.getSwipeRefreshLayout().setColorSchemeColors(getResources().getColor(R.color.black));

        refreshView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        pagerSize=1;
                        //获取最新图片的方法
                        picListPresenter.getPicList(pagerSize);
//                        //模拟无数据界面
//                        if (mm == 1) {
//                            TextView textView = new TextView(getContext());
//                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
//                            textView.setLayoutParams(params);
//                            textView.setGravity(Gravity.CENTER);
//                            textView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    refreshView.setRefreshing(true);
//                                }
//                            });
//
//                            textView.setText("自定义的无数据界面");
//                            refreshView.setCreateView(textView);
//                            refreshView.setEmptyView("暂无数据");
//                            refreshView.complete();
//                            return;
//                        }
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pagerSize++;
                        //加载下一页图片的方法
                        picListPresenter.getPicList(pagerSize);
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        refreshView.setRefreshing(true);
    }


    private PicListView picListView = new PicListView() {
        @Override
        public void onSuccess(PicList pic) {
            data.addAll(pic.getResults());
            refreshView.complete();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {
            ToastyUtil.showError(result);
            refreshView.setErrorView();
            refreshView.complete();
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        picListPresenter.onStop();
    }


    private void setupSearchBar() {
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            //当输入文字时调用的方法
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();


                    //根据输入的内容查询关键字
                    //simulates a query call to a data source
                    //with a new query.
                    KeyDataHelper.findSuggestions(getActivity(), newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY, new KeyDataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<KeySuggestion> results) {

                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mSearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mSearchView.hideProgress();
                                }
                            });
                }

                LogUtils.d( "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {
                KeySuggestion colorSuggestion = (KeySuggestion) searchSuggestion;

                KeyDataHelper.findResult(getActivity(), colorSuggestion.getBody(),
                        new KeyDataHelper.OnFindResultsListener() {
                            @Override
                            public void onResults(List<PicListBean> results) {
                                //show search results
                                LogUtils.e( results);

                            }

                        });
                LogUtils.d( "onSuggestionClicked()");

                mLastQuery = searchSuggestion.getBody();
            }


            //点击回车进行搜索时。。。。
            @Override
            public void onSearchAction(String query) {
                mLastQuery = query;

                KeyDataHelper.findResult(getActivity(), query,
                        new KeyDataHelper.OnFindResultsListener() {
                            @Override
                            public void onResults(List<PicListBean> results) {
                                 //show search results
                                LogUtils.e( results);
                            }

                        });
                LogUtils.d( "onSearchAction()");
            }
        });



        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {

            //点击输入框时...
            @Override
            public void onFocus() {

                //show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(KeyDataHelper.getHistory(getActivity(), 3));

                LogUtils.d( "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                //set the title of the bar so that when focus is returned a new query begins
                mSearchView.setSearchBarTitle(mLastQuery);

                //you can also set setSearchText(...) to make keep the query there when not focused and when focus returns
                //mSearchView.setSearchText(searchSuggestion.getBody());
                LogUtils.d( "onFocusCleared()");
            }
        });


        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

//                if (item.getItemId() == R.id.action_change_colors) {
//
//                    mIsDarkSearchTheme = true;
//
//                    //demonstrate setting colors for items
//                    mSearchView.setBackgroundColor(Color.parseColor("#787878"));
//                    mSearchView.setViewTextColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setHintTextColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setActionMenuOverflowColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setMenuItemIconColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setClearBtnColor(Color.parseColor("#e9e9e9"));
//                    mSearchView.setDividerColor(Color.parseColor("#BEBEBE"));
//                    mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
//                } else {
//
//                    //just print action
                    Toast.makeText(getActivity().getApplicationContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
//                }

            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                LogUtils.d("onHomeClicked()");
            }
        });

        /*
         * Here you have access to the left icon and the text of a given suggestion
         * item after as it is bound to the suggestion list. You can utilize this
         * callback to change some properties of the left icon and the text. For example, you
         * can load the left icon images using your favorite image loading library, or change text color.
         *
         *
         * Important:
         * Keep in mind that the suggestion list is a RecyclerView, so views are reused for different
         * items in the list.
         */
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                KeySuggestion colorSuggestion = (KeySuggestion) item;

                String textColor = mIsDarkSearchTheme ? "#ffffff" : "#000000";
                String textLight = mIsDarkSearchTheme ? "#bfbfbf" : "#787878";

                if (colorSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = colorSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font color=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(Html.fromHtml(text));
            }

        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mSearchView.setTranslationY(verticalOffset);
    }

    @Override
    public boolean onActivityBackPress() {
        //if mSearchView.setSearchFocused(false) causes the focused search
        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
        //returns false, we know that the search was already closed so the call didn't change the focus
        //state and it makes sense to call supper onBackPressed() and close the activity
        return mSearchView.setSearchFocused(false);
    }

    private void setupDrawer() {
        attachSearchViewActivityDrawer(mSearchView);
    }
}
