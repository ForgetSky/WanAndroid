package com.forgetsky.wanandroid.modules.main.contract;

import com.tbruyelle.rxpermissions2.RxPermissions;


public interface ArticleDetailContract {

    interface View extends CollectEventContract.View {
        void shareArticle();

        void shareError();

    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void shareEventWithPermissionVerify(RxPermissions rxPermissions);
    }
}
