package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.AbstractView;

public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {
    @Override
    public void attachView(AbstractView view) {

    }

    @Override
    public void detachView() {

    }
}
