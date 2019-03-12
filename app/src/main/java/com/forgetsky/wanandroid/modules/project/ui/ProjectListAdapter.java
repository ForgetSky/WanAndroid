package com.forgetsky.wanandroid.modules.project.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.utils.GlideImageLoader;

import java.util.List;

public class ProjectListAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {


    public ProjectListAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {
        helper.setText(R.id.item_project_list_title_tv, Html.fromHtml(item.getTitle()))
                .setText(R.id.item_project_list_author_tv, item.getAuthor())
        .setImageResource(R.id.item_project_list_like_iv, item.isCollect() ? R.drawable.ic_like : R.drawable.ic_like_not);
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.item_project_list_content_tv, Html.fromHtml(item.getDesc()));
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_project_list_time_tv, item.getNiceDate());
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideImageLoader.load(mContext, item.getEnvelopePic(),
                    helper.getView(R.id.item_project_list_iv));
        }

        helper.addOnClickListener(R.id.item_project_list_like_iv);

    }
}
