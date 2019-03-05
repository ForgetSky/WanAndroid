package com.forgetsky.wanandroid.modules.login.ui;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.login.contract.RegisterFragmentContract;
import com.forgetsky.wanandroid.modules.login.presenter.RegisterFragmentPresenter;

/**
 * @author: ForgetSky
 * @date: 2019/3/5
 */
public class RegisterFragment extends BaseFragment<RegisterFragmentPresenter> implements RegisterFragmentContract.View {
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initEventAndData() {

    }
}
