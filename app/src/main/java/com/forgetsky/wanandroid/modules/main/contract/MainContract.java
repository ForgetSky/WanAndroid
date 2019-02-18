package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;

public interface MainContract {
    interface View extends IView {
        /**
         * Show logout success
         */
        void showLogoutSuccess();
    }

    interface Presenter extends IPresenter<View> {
        /**
         * Set current page
         *
         * @param page current page
         */
        void setCurrentPage(int page);

    }
}
