package com.forgetsky.wanandroid.homepager.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.homepager.contract.HomePagerContract;

import javax.inject.Inject;

public class HomePagerPresenter extends BasePresenter<HomePagerContract.View>
        implements HomePagerContract.Presenter {

    @Inject
    HomePagerPresenter() {
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
    public void attachView(HomePagerContract.View view) {
        super.attachView(view);
    }
}
