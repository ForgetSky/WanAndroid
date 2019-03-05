package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.login.contract.LoginContract;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{
    @Inject
    LoginPresenter() {
    }
}
