package com.larryzhang.fonp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.Views;
import com.apkfuns.logutils.LogUtils;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicList;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.fragment.FolderFragment;
import com.larryzhang.fonp.presenter.PicListPresenter;
import com.larryzhang.fonp.utils.PicassoHelper;
import com.larryzhang.fonp.utils.ToastyUtil;
import com.larryzhang.fonp.utils.Utils;
import com.larryzhang.fonp.view.PicListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  今日最佳，可翻页式的list
 * @author zhangqiang
 * @date 2018/3/15
 */

public class FolderAdapter extends ItemsAdapter<PicListBean, FolderAdapter.ViewHolder>
        implements View.OnClickListener {

    FolderFragment context;
    List<PicListBean> data;
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    String today = dateformat.format(new Date());

    PicListPresenter picListPresenter = new PicListPresenter(Utils.getContext());

    public FolderAdapter(FolderFragment context) {
        this.context= context;
        //加载今日top图片
        data = new ArrayList<>();
        picListPresenter.onCreate();
        picListPresenter.attachView(picListView);


        picListPresenter.getTodayList(1,today);

    }

    @Override
    protected ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(parent);
        holder.image.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(final ViewHolder holder, int position) {
        final PicListBean item = getItem(position);

        holder.image.setTag(R.id.list_item_image, item);
        PicassoHelper.loadPaintingImage(holder.image, item.getImg(),item.getColor());

//        GlideHelper.loadPaintingImage(holder.image, item.getImg());
        holder.title.setText(String.valueOf(item.getId()));
    }

    private PicListView picListView = new PicListView() {
        @Override
        public void onSuccess(PicList pic) {
            data.addAll(pic.getResults());
            setItemsList(data);
        }

        @Override
        public void onError(String result) {
            ToastyUtil.showError(result);
        }
    };

    //点击事件
    @Override
    public void onClick(View view) {
        final PicListBean item = (PicListBean) view.getTag(R.id.list_item_image);
        context.openDetails(view, item);
    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.folder_list_item));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
        }
    }

}
