package com.forgetsky.wanandroid.main.contract;

import com.forgetsky.wanandroid.base.presenter.AbstractPresenter;
import com.forgetsky.wanandroid.base.view.AbstractView;

public interface MainContract {
    interface View extends AbstractView {
        /**
         * Show logout success
         */
        void showLogoutSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {
        /**
         * Set current page
         *
         * @param page current page
         */
        void setCurrentPage(int page);

    }
}
