package com.forgetsky.wanandroid.wxarticle.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;

public interface WxArticleContract {
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
