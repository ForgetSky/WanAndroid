package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.core.DataManager;

import javax.inject.Inject;

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Inject
    public DataManager mDataManager;

    @Override
    public void attachView(T view) {
        this.mView = view;

    }

    @Override
    public void detachView() {
        this.mView = null;

    }

    @Override
    public int getCurrentPage() {
//        return mDataManager.getCurrentPage();
        return 0;
    }
}
