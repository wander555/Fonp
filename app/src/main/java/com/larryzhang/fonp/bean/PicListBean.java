package com.larryzhang.fonp.bean;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;

/**
 * 最新图片
 * @author zhangqiang
 * @date 2018/3/14
 */

public class PicListBean {

    /**
     * id : 617852
     * img : https://img4.goodfon.com/wallpaper/mobile-s/c/7c/priroda-makro-inei-pautina.jpg
     * color : #333333
     */

    private int id;
    private String img;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    //获取url最后的文件详细名称
    public String[] getTag(){
        String url= this.getImg();
        return url.split(".com")[1].split("/")[5].split(".jpg")[0].split("-");
    }

    public PicListBean(int id, String img, String color) {
        this.id = id;
        this.img = img;
        this.color = color;
    }
}
