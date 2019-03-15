package com.forgetsky.wanandroid.modules.hierarchy.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.modules.hierarchy.bean.KnowledgeTreeData;

import java.util.List;

public class KnowledgeTreeAdapter extends BaseQuickAdapter<KnowledgeTreeData, BaseViewHolder> {


    public KnowledgeTreeAdapter(int layoutResId, @Nullable List<KnowledgeTreeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeTreeData item) {
        if(item.getName() == null) {
            return;
        }
        helper.setText(R.id.knowledge_title, item.getName());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder childTitles = new StringBuilder();
        for (KnowledgeTreeData data: item.getChildren()) {
            childTitles.append(data.getName()).append("   ");
        }
        helper.setText(R.id.title_child, childTitles.toString());

    }
}
