package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.IView;

public interface IPresenter<T extends IView> {

    /**
     * attachView
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * detachView
     */
    void detachView();

}
