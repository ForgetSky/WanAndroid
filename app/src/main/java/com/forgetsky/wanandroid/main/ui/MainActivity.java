package com.forgetsky.wanandroid.main.ui;

import android.os.Bundle;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.main.contract.MainContract;
import com.forgetsky.wanandroid.main.presenter.MainPresenter;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
