package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.login.contract.LoginFragmentContract;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginFragmentPresenter extends BasePresenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter{
    @Inject
    LoginFragmentPresenter() {
    }
}
