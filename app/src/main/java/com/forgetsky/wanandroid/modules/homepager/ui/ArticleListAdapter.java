package com.forgetsky.wanandroid.modules.homepager.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;

import java.util.List;

public class ArticleListAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {


    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()))
        .setText(R.id.tv_article_author, item.getAuthor());
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tv_article_chapterName, classifyName);
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_niceDate, item.getNiceDate());
        }
    }
}
