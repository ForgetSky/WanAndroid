package com.forgetsky.wanandroid.project.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;

public interface ProjectContract {
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
