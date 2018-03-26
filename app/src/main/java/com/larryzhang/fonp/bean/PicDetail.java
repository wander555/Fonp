package com.larryzhang.fonp.bean;

import java.util.List;

/**
 * File description.
 *
 * @author zhangqiang
 * @date 2018/3/26
 */

public class PicDetail {

    /**
     * colors : [{"id":208,"color":"000000","name":"black"},{"id":129,"color":"666633","name":"dark olive"},{"id":170,"color":"333333","name":"anthracite"},{"id":171,"color":"333300","name":"dark khaki"},{"id":203,"color":"003300","name":"dark green"},{"id":86,"color":"999966","name":"dark yellow-brown"},{"id":164,"color":"336633","name":"green fern"},{"id":135,"color":"663333","name":"brown"},{"id":207,"color":"000033","name":"sapphire"},{"id":176,"color":"330000","name":"brown"}]
     * fon_stat : {"downloads":95,"votings":3,"comments":0,"favorites":3}
     * tags : [{"id":1297,"slug":"saturn","name":"Saturn"},{"id":819,"slug":"vid","name":"view"},{"id":319565,"slug":"s-temnoi-storony","name":"from the dark side"}]
     * similar : [{"id":372496,"img":"https://img3.goodfon.com/wallpaper/big/4/66/gory-vid-devushka.jpg","color":"#333300"},{"id":207186,"img":"https://img1.goodfon.com/wallpaper/big/5/58/samolet-krylo-pilot-vysota-vid.jpg","color":"#333333"},{"id":167336,"img":"https://img2.goodfon.com/wallpaper/big/d/58/leaves-park-alley-trees-4751.jpg","color":"#663300"},{"id":32780,"img":"https://img3.goodfon.com/wallpaper/big/7/c1/peyzazhi-kitay-holmy-trava.jpg","color":"#666666"}]
     * id : 606601
     * img_big : https://img4.goodfon.com/wallpaper/big/e/35/saturn-vid-s-temnoi-storony.jpg
     * img_or : https://img4.goodfon.com/original/-x-/e/35/saturn-vid-s-temnoi-storony.jpg
     * catalog : {"id":6,"slug":"space","name":"Space"}
     * width : 6672
     * height : 3104
     * created_by : {"id":111115,"username":"aguila","avatar":"https://img4.goodfon.ru/avatars/s6umo4jgcx.png"}
     * published_on : 2018-03-26T06:15:06.791561
     * in_favorite : false
     * url : https://www.goodfon.com/wallpaper/saturn-vid-s-temnoi-storony.html
     * slug : saturn-vid-s-temnoi-storony
     * favorites : [{"id":436003,"username":"kodt","avatar":null},{"id":448189,"username":"hatefggtsnmslms","avatar":"https://img4.goodfon.ru/avatars/fjr5ixl9h1.png"},{"id":311660,"username":"salass","avatar":null}]
     */
    /*网站统计：下载*/
    private FonStatBean fon_stat;
    private int id;
    private String img_big;
    private String img_or;
    /*最主要关键字统计*/
    private CatalogBean catalog;
    private int width;
    private int height;
    /*作者信息*/
    private CreatedByBean created_by;
    private String published_on;
    private boolean in_favorite;
    private String url;
    private String slug;
    private List<ColorsBean> colors;
    /*所有关键字统计*/
    private List<TagsBean> tags;
    /*相似图片*/
    private List<SimilarBean> similar;
    /*喜欢这个图片的用户*/
    private List<FavoritesBean> favorites;

    public FonStatBean getFon_stat() {
        return fon_stat;
    }

    public void setFon_stat(FonStatBean fon_stat) {
        this.fon_stat = fon_stat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_big() {
        return img_big;
    }

    public void setImg_big(String img_big) {
        this.img_big = img_big;
    }

    public String getImg_or() {
        return img_or;
    }

    public void setImg_or(String img_or) {
        this.img_or = img_or;
    }

    public CatalogBean getCatalog() {
        return catalog;
    }

    public void setCatalog(CatalogBean catalog) {
        this.catalog = catalog;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CreatedByBean getCreated_by() {
        return created_by;
    }

    public void setCreated_by(CreatedByBean created_by) {
        this.created_by = created_by;
    }

    public String getPublished_on() {
        return published_on;
    }

    public void setPublished_on(String published_on) {
        this.published_on = published_on;
    }

    public boolean isIn_favorite() {
        return in_favorite;
    }

    public void setIn_favorite(boolean in_favorite) {
        this.in_favorite = in_favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<ColorsBean> getColors() {
        return colors;
    }

    public void setColors(List<ColorsBean> colors) {
        this.colors = colors;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<SimilarBean> getSimilar() {
        return similar;
    }

    public void setSimilar(List<SimilarBean> similar) {
        this.similar = similar;
    }

    public List<FavoritesBean> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoritesBean> favorites) {
        this.favorites = favorites;
    }

    public static class FonStatBean {
        /**
         * downloads : 95
         * votings : 3
         * comments : 0
         * favorites : 3
         */

        private int downloads;
        private int votings;
        private int comments;
        private int favorites;

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public int getVotings() {
            return votings;
        }

        public void setVotings(int votings) {
            this.votings = votings;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }
    }

    public static class CatalogBean {
        /**
         * id : 6
         * slug : space
         * name : Space
         */

        private int id;
        private String slug;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CreatedByBean {
        /**
         * id : 111115
         * username : aguila
         * avatar : https://img4.goodfon.ru/avatars/s6umo4jgcx.png
         */

        private int id;
        private String username;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class ColorsBean {
        /**
         * id : 208
         * color : 000000
         * name : black
         */

        private int id;
        private String color;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        /**
         * id : 1297
         * slug : saturn
         * name : Saturn
         */

        private int id;
        private String slug;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SimilarBean {
        /**
         * id : 372496
         * img : https://img3.goodfon.com/wallpaper/big/4/66/gory-vid-devushka.jpg
         * color : #333300
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
    }

    public static class FavoritesBean {
        /**
         * id : 436003
         * username : kodt
         * avatar : null
         */

        private int id;
        private String username;
        private Object avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }
    }
}
