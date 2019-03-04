package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.greendao.HistoryData;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.contract.SearchContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;


public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    SearchPresenter() {
    }

    @Override
    public void getTopSearchData() {
        addSubscribe(mDataManager.getTopSearchData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<TopSearchData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_banner_data),
                        false) {
                    @Override
                    public void onSuccess(List<TopSearchData> usefulSiteData) {
                        mView.showTopSearchData(usefulSiteData);
                    }
                }));
    }

    @Override
    public void addHistoryData(String data) {
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryData>>) e -> {
            List<HistoryData> historyDataList = mDataManager.addHistoryData(data);
            e.onNext(historyDataList);
        }).compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribe(historyDataList -> {
                        }
                ));
    }

    @Override
    public void clearAllHistoryData() {
        mDataManager.clearAllHistoryData();
    }

    @Override
    public void deleteHistoryDataById(Long id) {
        mDataManager.deleteHistoryDataById(id);
    }

    @Override
    public void loadAllHistoryData() {
        addSubscribe(Observable.create((ObservableOnSubscribe<List<HistoryData>>) e -> {
            List<HistoryData> historyDataList = mDataManager.loadAllHistoryData();
            e.onNext(historyDataList);
        }).compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribe(historyDataList -> {
                            Collections.reverse(historyDataList);
                            mView.showHistoryData(historyDataList);
                        }
                ));
    }
}
