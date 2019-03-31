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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.bean.TodoListData;
import com.forgetsky.wanandroid.modules.todo.contract.TodoListContract;
import com.forgetsky.wanandroid.modules.todo.presenter.TodoListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ForgetSky on 19-3-29.
 */
public class TodoListFragment extends BaseFragment<TodoListPresenter> implements TodoListContract.View {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.todo_list_recycler_view)
    RecyclerView mRecyclerView;

    private TodoListAdapter mAdapter;
    private int type = 0;
    private int status = 0;
    private int lastStatus = status;
    private boolean isVisible = false;
    private boolean isLoaded = false;

    public static TodoListFragment newInstance(Bundle bundle) {
        TodoListFragment fragment = new TodoListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo_list;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        type = getArguments().getInt(Constants.TODO_TYPE);
        //使用最新状态
        status = TodoActivity.getTodoStatus();
        initRefreshLayout();
        mPresenter.refreshLayout(type, status,true);
        isLoaded =true;

    }

    private void initRecyclerView() {
        List<TodoItemData> mTodoItemDataList = new ArrayList<>();
        mAdapter = new TodoListAdapter(R.layout.item_todo_list, mTodoItemDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> openEditTodo(view, position));
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                return false;
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshLayout(type, status, false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    private void openEditTodo(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        //todo
    }

    @Override
    public void showTodoListData(TodoListData todoListData, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mAdapter.replaceData(todoListData.getDatas());
        } else {
            mAdapter.addData(todoListData.getDatas());
        }
    }

    /**
     * TODO状态改变后，若当前Fragment可见，直接更新数据
     * @param status
     */
    @Override
    public void todoStatusChange(int status) {
        lastStatus = this.status;
        this.status = status;
        if (isVisible) {
            mPresenter.refreshLayout(type, status, true);
        }
    }

    /**
     * TODO状态改变后，Fragment再次可见，则更新数据
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (isVisibleToUser && isLoaded && status != lastStatus) {
            status = TodoActivity.getTodoStatus();
            lastStatus = status;
            mPresenter.refreshLayout(type, status, true);
        }
    }

}
