package com.forgetsky.wanandroid.modules.login.ui;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.modules.login.contract.LoginContract;
import com.forgetsky.wanandroid.modules.main.presenter.MainPresenter;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginActivity extends BaseActivity<MainPresenter> implements LoginContract.View{
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }
}
