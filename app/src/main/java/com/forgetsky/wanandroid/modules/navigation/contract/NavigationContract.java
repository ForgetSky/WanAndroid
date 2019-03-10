package com.forgetsky.wanandroid.modules.navigation.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;

import java.util.List;

public interface NavigationContract {
    interface View extends IView {
        void showNavigationListData(List<NavigationListData> navigationListData);
    }

    interface Presenter extends IPresenter<View> {
        void getNavigationListData(boolean isShowError);
    }
}
