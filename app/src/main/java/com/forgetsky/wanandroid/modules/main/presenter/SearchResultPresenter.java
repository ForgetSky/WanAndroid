package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.main.contract.SearchResultContract;

import javax.inject.Inject;


public class SearchResultPresenter extends BasePresenter<SearchResultContract.View> implements SearchResultContract.Presenter {

    @Inject
    SearchResultPresenter() {
    }

}
