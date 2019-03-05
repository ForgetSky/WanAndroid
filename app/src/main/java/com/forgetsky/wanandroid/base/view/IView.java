package com.forgetsky.wanandroid.base.view;

public interface IView {
    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    void showLoading();

    void hideLoading();
}
