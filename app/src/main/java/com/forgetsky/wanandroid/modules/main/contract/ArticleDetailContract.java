package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;


public interface ArticleDetailContract {

    interface View extends IView {
        void shareArticle();

        void shareError();

    }

    interface Presenter extends IPresenter<View> {

        void shareEventWithPermissionVerify(RxPermissions rxPermissions);
    }
}
