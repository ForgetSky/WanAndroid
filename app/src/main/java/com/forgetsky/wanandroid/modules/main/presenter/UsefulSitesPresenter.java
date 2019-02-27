package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;
import com.forgetsky.wanandroid.modules.main.contract.UsefulSitesContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;


public class UsefulSitesPresenter extends BasePresenter<UsefulSitesContract.View> implements UsefulSitesContract.Presenter {

    @Inject
    UsefulSitesPresenter() {
    }


    @Override
    public void getUsefulSites() {
        addSubscribe(mDataManager.getUsefulSites()
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<UsefulSiteData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_banner_data),
                        false) {
                    @Override
                    public void onSuccess(List<UsefulSiteData> usefulSiteData) {
                        mView.showUsefulSites(usefulSiteData);
                    }
                }));
    }
}
