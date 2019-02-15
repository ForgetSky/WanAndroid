package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.IView;

public interface IPresenter<T extends IView> {

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
