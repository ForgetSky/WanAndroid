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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.RefreshTodoEvent;
import com.forgetsky.wanandroid.modules.todo.contract.TodoContract;
import com.forgetsky.wanandroid.modules.todo.presenter.TodoPresenter;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ForgetSky on 19-3-29.
 */
public class TodoActivity extends BaseActivity<TodoPresenter> implements TodoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.todo_bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.todo_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.todo_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.todo_viewpager)
    ViewPager mViewPager;

    private SparseArray<TodoListFragment> fragmentSparseArray = new SparseArray<>(5);
    private SparseArray<String> mTodoTypeArray = new SparseArray<>(5);

    private static int mTodoStatus = 0;

    public static int getTodoStatus() {
        return mTodoStatus;
    }

    @Override
    protected void initView() {
        mTodoStatus = 0;
        initBottomNavigationView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_todo;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.todo_title);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        initTodoTypeList();
        initViewPagerAndTabLayout();
    }

    private void initTodoTypeList() {
        mTodoTypeArray.put(Constants.TODO_TYPE_ALL, getString(R.string.todo_all));
        mTodoTypeArray.put(Constants.TODO_TYPE_WORK, getString(R.string.todo_work));
        mTodoTypeArray.put(Constants.TODO_TYPE_STUDY, getString(R.string.todo_study));
        mTodoTypeArray.put(Constants.TODO_TYPE_LIFE, getString(R.string.todo_life));
        mTodoTypeArray.put(Constants.TODO_TYPE_OTHER, getString(R.string.todo_other));
    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TodoListFragment todoListFragment = fragmentSparseArray.get(position);
                if (todoListFragment != null) {
                    return todoListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.TODO_TYPE, position);
                    todoListFragment = TodoListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position, todoListFragment);
                    return todoListFragment;
                }
            }

            @Override
            public int getCount() {
                return mTodoTypeArray == null ? 0 : mTodoTypeArray.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTodoTypeArray.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_not_todo:
                    if (mTodoStatus == 1) {
                        mTodoStatus = 0;
                        EventBus.getDefault().post(new RefreshTodoEvent(0));
                    }
                    break;
                case R.id.action_todo_done:
                    if (mTodoStatus == 0) {
                        mTodoStatus = 1;
                        EventBus.getDefault().post(new RefreshTodoEvent(1));
                    }
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @OnClick({R.id.todo_floating_action_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.todo_floating_action_btn:
                Intent intent = new Intent(TodoActivity.this, AddTodoActivity.class);
                intent.putExtra(Constants.TODO_TYPE, mViewPager.getCurrentItem());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
