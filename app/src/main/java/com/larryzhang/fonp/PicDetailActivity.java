package com.larryzhang.fonp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import immortalz.me.library.TransitionsHeleper;
import immortalz.me.library.bean.InfoBean;
import immortalz.me.library.method.ColorShowMethod;

public class PicDetailActivity extends AppCompatActivity {

    @Bind(R.id.details_image)
    ImageView detailsImage;
    @Bind(R.id.details_title)
    TextView detailsTitle;
    @Bind(R.id.details_text)
    TextView detailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);
        ButterKnife.bind(this);

        TransitionsHeleper.build(this)
                .setShowMethod(new ColorShowMethod(R.color.black, R.color.black) {
                    @Override
                    public void loadPlaceholder(InfoBean bean, ImageView placeholder) {
                        Glide.with(PicDetailActivity.this)
                                .load(bean.getLoad())
                                .centerCrop()
                                .into(placeholder);
                    }

                    @Override
                    public void loadTargetView(InfoBean bean, View targetView) {
                        Glide.with(PicDetailActivity.this)
                                .load(bean.getLoad())
                                .centerCrop()
                                .into((ImageView) targetView);
                        detailsTitle.setText("immortalz");
                    }
                })
                .setExposeColor(getResources().getColor(R.color.black))
                .intoTargetView(detailsImage)
                .show();

    }
}
