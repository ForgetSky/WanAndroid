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

package com.forgetsky.wanandroid.modules.todo.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.contract.AddTodoContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.HashMap;

import javax.inject.Inject;

public class AddTodoPresenter extends BasePresenter<AddTodoContract.View>
        implements AddTodoContract.Presenter {

    @Inject
    AddTodoPresenter() {
    }

    @Override
    public void addTodo(HashMap<String, Object> map) {
        addSubscribe(mDataManager.addTodo(map)
                .compose(RxUtils.SchedulerTransformer())
                .filter(todoItemData -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemData>(mView,
                        WanAndroidApp.getContext().getString(R.string.add_todo_failed),
                        true) {
                    @Override
                    public void onSuccess(TodoItemData todoItemData) {
                        mView.addTodoSuccess(todoItemData);
                    }
                }));
    }

    @Override
    public void updateTodo(int id, HashMap<String, Object> map) {
        addSubscribe(mDataManager.updateTodo(id, map)
                .compose(RxUtils.SchedulerTransformer())
                .filter(todoItemData -> mView != null)
                .subscribeWith(new BaseObserver<TodoItemData>(mView,
                        WanAndroidApp.getContext().getString(R.string.update_todo_failed),
                        false) {
                    @Override
                    public void onSuccess(TodoItemData todoItemData) {
                        mView.updateTodoSuccess(todoItemData);
                    }
                }));
    }
}
