package com.forgetsky.wanandroid.modules.main.presenter;

import android.Manifest;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.main.contract.ArticleDetailContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;


public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Inject
    ArticleDetailPresenter() {
    }

    @Override
    public void shareEventWithPermissionVerify(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        mView.shareArticle();
                    } else {
                        mView.shareError();
                    }
                }));
    }
}
