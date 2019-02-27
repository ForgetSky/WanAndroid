package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;

import java.util.List;


public interface UsefulSitesContract {

    interface View extends IView {

        void showUsefulSites(List<UsefulSiteData> usefulSiteData);

    }

    interface Presenter extends IPresenter<View> {

        void getUsefulSites();
    }
}
