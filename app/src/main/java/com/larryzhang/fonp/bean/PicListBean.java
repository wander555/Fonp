package com.larryzhang.fonp.bean;

/**
 *图片列表
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

    public PicListBean(int id, String img, String color) {
        this.id = id;
        this.img = img;
        this.color = color;
    }
}
