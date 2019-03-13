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

    void reload();

    void registerEventBus();

    void unregisterEventBus();

    /**
     * Set login status
     *
     * @param loginStatus login status
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * Get login status
     *
     * @return if is login status
     */
    boolean getLoginStatus();

    /**
     * Get login account
     *
     * @return login account
     */
    String getLoginAccount();

    /**
     * Set login status
     *
     * @param account account
     */
    void setLoginAccount(String account);

}
