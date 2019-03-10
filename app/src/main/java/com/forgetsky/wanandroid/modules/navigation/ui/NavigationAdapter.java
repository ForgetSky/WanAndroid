package com.forgetsky.wanandroid.modules.navigation.ui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;


public class NavigationAdapter extends BaseQuickAdapter<NavigationListData, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationListData item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<ArticleItemData> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<ArticleItemData>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, ArticleItemData articleItemData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (articleItemData == null) {
                    return null;
                }
                String name = articleItemData.getTitle();
                tv.setText(name);
                tv.setTextColor(CommonUtils.getRandomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    CommonUtils.startArticleDetailActivity(parent.getContext(),
                            mArticles.get(position1).getId(),
                            mArticles.get(position1).getTitle().trim(),
                            mArticles.get(position1).getLink().trim());
                    return true;
                });
                return tv;
            }
        });
    }
}
