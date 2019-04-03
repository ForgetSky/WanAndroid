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

package com.forgetsky.wanandroid.modules.todo.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.bean.TodoListData;

public interface TodoListContract {

    interface View extends IView {
        void showTodoListData(TodoListData todoListData, boolean isRefresh);

        void todoStatusChange(int status);

        void updateTodoStatusSuccess(TodoItemData todoItemData);

        void deleteTodoSuccess(TodoItemData todoItemData);
    }

    interface Presenter extends IPresenter<View> {
        void refreshLayout(int type, int status, boolean isShowStatusView);

        void getTodoListData(boolean isShowStatusView);

        void loadMore();

        void updateTodoStatus(int id, int status);

        void deleteTodo(int id);
    }
}
