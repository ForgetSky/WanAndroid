package com.forgetsky.wanandroid.modules.navigation.bean;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;

import java.io.Serializable;
import java.util.List;

public class NavigationListData implements Serializable {

    private List<ArticleItemData> articles;
    private int cid;
    private String name;

    public List<ArticleItemData> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleItemData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
