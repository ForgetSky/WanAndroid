package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.main.contract.CommonContract;

import javax.inject.Inject;


public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {

    @Inject
    CommonPresenter() {
    }

}
