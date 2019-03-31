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

package com.forgetsky.wanandroid.modules.todo.ui;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;

import java.util.List;

public class TodoListAdapter extends BaseQuickAdapter<TodoItemData, BaseViewHolder> {


    public TodoListAdapter(int layoutResId, @Nullable List<TodoItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoItemData item) {
        helper.setText(R.id.tv_todo_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_todo_content, item.getContent());
        if (!TextUtils.isEmpty(item.getCompleteDateStr())) {
            helper.setText(R.id.tv_todo_date, item.getCompleteDateStr());
        } else {
            helper.setText(R.id.tv_todo_date, item.getDateStr());
        }

        if (item.getPriority() == 1) {
            helper.getView(R.id.tv_todo_priority).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_todo_priority).setVisibility(View.GONE);
        }

    }
}
