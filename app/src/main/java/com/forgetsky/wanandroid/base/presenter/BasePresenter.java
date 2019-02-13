package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.AbstractView;

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {
    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView = view;

    }

    @Override
    public void detachView() {
        this.mView = null;

    }
}
