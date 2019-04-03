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

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.RefreshTodoEvent;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.bean.TodoListData;
import com.forgetsky.wanandroid.modules.todo.contract.TodoListContract;
import com.forgetsky.wanandroid.modules.todo.presenter.TodoListPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.simple.eventbus.EventBus;

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
    TextView mTodoDelete;
    TextView mTodoChangeStatus;
    View popContentView;
    private TodoListAdapter mAdapter;
    private PopupWindow popupWindow;
    private int type = 0;
    private int status = 0;
    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isLoaded = false; //当前Fragment是否已经加载
    private boolean isNeedRefresh = false;
    private boolean isRemoveEvent = false;
    private int clickTodoId;
    private int clickTodoPosition;

    public static TodoListFragment newInstance(Bundle bundle) {
        TodoListFragment fragment = new TodoListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        popContentView = LayoutInflater.from(_mActivity).inflate(
                R.layout.todo_popup_window, null);
        mTodoChangeStatus = popContentView.findViewById(R.id.todo_change_status);
        mTodoDelete = popContentView.findViewById(R.id.todo_delete);
        mTodoChangeStatus.setOnClickListener(v -> {
            if (status == 0) {
                mPresenter.updateTodoStatus(clickTodoId, 1);
            } else {
                mPresenter.updateTodoStatus(clickTodoId, 0);
            }
            popupWindow.dismiss();
        });
        mTodoDelete.setOnClickListener(v -> {
            popupWindow.dismiss();
            AlertDialog alertDialog = new AlertDialog.Builder(_mActivity)
                    .setMessage(R.string.confirm_delete_todo)
                    .setPositiveButton(R.string.ok, (dialog, which) -> mPresenter.deleteTodo(clickTodoId))
                    .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    }).show();
        });
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
        mPresenter.refreshLayout(type, status, true);
        isLoaded = true;

    }

    private void initRecyclerView() {
        List<TodoItemData> mTodoItemDataList = new ArrayList<>();
        mAdapter = new TodoListAdapter(R.layout.item_todo_list, mTodoItemDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> openEditTodo(view, position));
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                clickTodoId = mAdapter.getData().get(position).getId();
                clickTodoPosition =position;
                if (status == 1) {
                    mTodoChangeStatus.setText(R.string.marked_todo);
                } else {
                    mTodoChangeStatus.setText(R.string.marked_completed);
                }
                popupWindow = CommonUtils.showPopupWindow(view, popContentView);
                return true;
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
        Intent intent = new Intent(_mActivity, AddTodoActivity.class);
        intent.putExtra(Constants.TODO_DATA, mAdapter.getData().get(position));
        _mActivity.startActivity(intent);
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

    @Override
    public void updateTodoStatusSuccess(TodoItemData todoItemData) {
        mAdapter.remove(clickTodoPosition);
        isRemoveEvent = true;
        EventBus.getDefault().post(new RefreshTodoEvent(-1));
        ToastUtils.showToast(_mActivity, getString(R.string.update_todo_status_success));
    }

    @Override
    public void deleteTodoSuccess(TodoItemData todoItemData) {
        mAdapter.remove(clickTodoPosition);
        isRemoveEvent = true;
        EventBus.getDefault().post(new RefreshTodoEvent(-1));
        ToastUtils.showToast(_mActivity, getString(R.string.delete_todo_success));
    }

    /**
     * TODO状态改变后，若当前Fragment可见，直接更新数据
     *
     * @param status
     */
    @Override
    public void todoStatusChange(int status) {
        //切换已完成/未完成状态时，对this.status重新赋值，刷新更新status后的数据
        // 其他情况刷新当前status的数据
        if (status == 0 || status == 1) {
            this.status = status;
        }
        if (isVisible) {
            if (!isRemoveEvent) {
                mPresenter.refreshLayout(type, this.status, true);
            } else {
                //已经删除对应item,则不用再刷新数据
                isRemoveEvent = false;
            }
        } else {
            isNeedRefresh = true;
        }
    }

    /**
     * TODO状态改变后，Fragment再次可见，则更新数据
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (isVisibleToUser && isLoaded && isNeedRefresh) {
            isNeedRefresh = false;
            mPresenter.refreshLayout(type, status, true);
        }
    }

}
