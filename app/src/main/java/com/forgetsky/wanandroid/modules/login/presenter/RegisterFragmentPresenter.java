package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.login.contract.RegisterFragmentContract;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class RegisterFragmentPresenter extends BasePresenter<RegisterFragmentContract.View> implements RegisterFragmentContract.Presenter{
    @Inject
    RegisterFragmentPresenter() {
    }
}
