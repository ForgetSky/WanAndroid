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

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.modules.todo.contract.TodoContract;
import com.forgetsky.wanandroid.modules.todo.presenter.TodoPresenter;

import butterknife.BindView;

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

    @Override
    protected void initView() {
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

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_not_todo:

                    break;
                case R.id.action_todo_done:

                    break;

                default:
                    break;
            }
            return true;
        });
    }

    @Override
    protected void initEventAndData() {

    }
}
