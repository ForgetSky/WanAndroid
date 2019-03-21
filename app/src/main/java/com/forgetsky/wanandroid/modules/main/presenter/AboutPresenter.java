package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.main.contract.AboutContract;

import javax.inject.Inject;


public class AboutPresenter extends BasePresenter<AboutContract.View> implements AboutContract.Presenter {

    @Inject
    AboutPresenter() {
    }

}
