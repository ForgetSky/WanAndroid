package com.forgetsky.wanandroid.modules.navigation.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.navigation.contract.NavigationContract;

import javax.inject.Inject;

public class NavigationPresenter extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter {

    @Inject
    NavigationPresenter() {
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

}
