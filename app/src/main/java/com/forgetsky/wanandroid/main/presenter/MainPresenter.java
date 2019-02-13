package com.forgetsky.wanandroid.main.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.main.contract.MainContract;

import javax.inject.Inject;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    MainPresenter() {
        super();
    }
    @Override
    public void setCurrentPage(int page) {

    }
}
