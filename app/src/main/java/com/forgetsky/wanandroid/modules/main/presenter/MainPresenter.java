package com.forgetsky.wanandroid.modules.main.presenter;

import android.util.Log;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.main.contract.MainContract;

import javax.inject.Inject;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    MainPresenter() {
    }
    @Override
    public void setCurrentPage(int page) {

    }
}
