package com.larryzhang.fonp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.Views;
import com.larryzhang.fonp.R;
import com.larryzhang.fonp.bean.PicListBean;
import com.larryzhang.fonp.fragment.FolderFragment;
import com.larryzhang.fonp.utils.PicassoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhangqiang
 * @date 2018/3/15
 */

public class FolderAdapter extends ItemsAdapter<PicListBean, FolderAdapter.ViewHolder>
        implements View.OnClickListener {

    FolderFragment context;

    public FolderAdapter(FolderFragment context) {

        //TODO
        List<PicListBean> data = new ArrayList<>();
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg","#666633"));
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));
        data.add(new PicListBean(617852,"https://img4.goodfon.com/wallpaper/mobile-s/3/89/klubnika-tart-sweet-iagody-delicious-berries-chernika-slad-8.jpg","#333333"));

//        setItemsList(Arrays.asList(PicListBean.getAllPaintings(context.getResources())));
        this.context= context;
        setItemsList(data);
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
