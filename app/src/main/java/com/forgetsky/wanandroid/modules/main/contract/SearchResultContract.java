package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;


public interface SearchResultContract {

    interface View extends IView {
//        void showTopSearchData(List<TopSearchData> usefulSiteData);

    }

    interface Presenter extends IPresenter<View> {
//        void getTopSearchData();
    }
}
