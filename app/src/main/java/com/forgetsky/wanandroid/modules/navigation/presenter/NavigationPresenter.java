package com.forgetsky.wanandroid.modules.navigation.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;
import com.forgetsky.wanandroid.modules.navigation.contract.NavigationContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class NavigationPresenter extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter {

    @Inject
    NavigationPresenter() {
    }


    @Override
    public void getNavigationListData(boolean isShowError) {
        addSubscribe(mDataManager.getNavigationListData()
                .compose(RxUtils.SchedulerTransformer())
                .filter( navigationListData-> mView != null)
                .subscribeWith(new BaseObserver<List<NavigationListData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_get_navigation_data),
                        isShowError) {
                    @Override
                    public void onSuccess(List<NavigationListData> navigationListData) {
                        mView.showNavigationListData(navigationListData);
                    }
                }));
    }
}
