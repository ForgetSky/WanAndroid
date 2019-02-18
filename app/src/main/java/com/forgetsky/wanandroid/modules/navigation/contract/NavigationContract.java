package com.forgetsky.wanandroid.modules.navigation.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;

public interface NavigationContract {
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

        int getCurrentPage();

    }
}
