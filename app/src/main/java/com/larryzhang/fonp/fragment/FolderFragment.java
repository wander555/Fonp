package com.larryzhang.fonp.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.adapter.FolderAdapter;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.utils.BackHandlerHelper;
import com.larryzhang.fonp.utils.GlideHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 最新图片
 * Created by Administrator on 2018/3/13.
 */

public class FolderFragment extends Fragment implements FragmentBackHandler{


    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.touch_interceptor_view)
    View touchInterceptorView;
    @Bind(R.id.details_image)
    ImageView detailsImage;
    @Bind(R.id.details_title)
    TextView detailsTitle;
    @Bind(R.id.details_text)
    TextView detailsText;
    @Bind(R.id.details_layout)
    LinearLayout detailsLayout;
    @Bind(R.id.unfoldable_view)
    UnfoldableView unfoldableView;

//    private List<PicListBean> data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_folder, container, false);
        ButterKnife.bind(this, view);

        listView.setAdapter(new FolderAdapter(this));

        touchInterceptorView.setClickable(false);
        detailsLayout.setVisibility(View.INVISIBLE);


        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                touchInterceptorView.setClickable(true);
                detailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                touchInterceptorView.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                touchInterceptorView.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                touchInterceptorView.setClickable(false);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }


    public void openDetails(View coverView, PicListBean painting) {

        GlideHelper.loadPaintingImage(detailsImage, painting.getImg());
        detailsTitle.setText(String.valueOf(painting.getColor()));

        SpannableBuilder builder = new SpannableBuilder(getContext());
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("id").append(": ")
                .clearStyle()
                .append(String.valueOf(painting.getId())).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD);
//                .append(R.string.location).append(": ")
//                .clearStyle()
//                .append(painting.getLocation());
        detailsText.setText(builder.build());

        unfoldableView.unfold(coverView, detailsLayout);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onBackPressed() {
        if (unfoldableView != null && (unfoldableView.isUnfolded() || unfoldableView.isUnfolding())) {
            unfoldableView.foldBack();
            return true;
        } else {
            return BackHandlerHelper.handleBackPress(this);
        }

    }
}
