package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.AbstractView;

public interface AbstractPresenter<T extends AbstractView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();
}
