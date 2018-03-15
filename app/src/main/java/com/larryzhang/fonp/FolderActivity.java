package com.larryzhang.fonp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.larryzhang.fonp.adapter.FolderAdapter;
import com.larryzhang.fonp.adapter.PaintingsAdapter;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.utils.GlideHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.description;


public class FolderActivity extends AppCompatActivity {


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
    private List<PicListBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        ButterKnife.bind(this);

        data = new ArrayList<>();
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg","#666633"));
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));

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


    }


    @Override
    public void onBackPressed() {
        if (unfoldableView != null && (unfoldableView.isUnfolded() || unfoldableView.isUnfolding())) {
            unfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }

    public void openDetails(View coverView, PicListBean painting) {

        GlideHelper.loadPaintingImage(detailsImage, painting.getImg());
        detailsTitle.setText(String.valueOf(painting.getColor()));

        SpannableBuilder builder = new SpannableBuilder(this);
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


}
