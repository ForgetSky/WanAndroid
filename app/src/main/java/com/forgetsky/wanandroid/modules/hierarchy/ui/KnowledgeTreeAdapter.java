/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
