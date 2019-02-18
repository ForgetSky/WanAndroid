package com.forgetsky.wanandroid.wxarticle.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.wxarticle.contract.WxArticleContract;

import javax.inject.Inject;

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View>
        implements WxArticleContract.Presenter {

    @Inject
    WxArticlePresenter() {
        super();
    }
    private int currentPage;

    @Override
    public void setCurrentPage(int page) {
        currentPage = page;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void attachView(WxArticleContract.View view) {
        super.attachView(view);
    }
}
