package com.forgetsky.wanandroid.modules.todo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.todo.contract.AddTodoContract;
import com.forgetsky.wanandroid.modules.todo.presenter.AddTodoPresenter;

import butterknife.BindView;

/**
 * Created by ForgetSky on 2019/3/31.
 */
public class AddTodoActivity extends BaseActivity<AddTodoPresenter> implements AddTodoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        String title = getIntent().getStringExtra(Constants.TODO_TITLE);
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }
}
