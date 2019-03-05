package com.forgetsky.wanandroid.modules.login.ui;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.login.contract.LoginFragmentContract;
import com.forgetsky.wanandroid.modules.login.presenter.LoginFragmentPresenter;

/**
 * @author: ForgetSky
 * @date: 2019/3/5
 */
public class LoginFragment extends BaseFragment<LoginFragmentPresenter> implements LoginFragmentContract.View {
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData() {

    }
}
