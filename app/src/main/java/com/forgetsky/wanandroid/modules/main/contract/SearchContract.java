package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.core.greendao.HistoryData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;

import java.util.List;


public interface SearchContract {

    interface View extends IView {
        void showTopSearchData(List<TopSearchData> usefulSiteData);
        void showHistoryData(List<HistoryData> historyDataList);
    }

    interface Presenter extends IPresenter<View> {
        void getTopSearchData();
        void addHistoryData(String data);
        void clearAllHistoryData();
        void deleteHistoryDataById(Long id);
        void loadAllHistoryData();
    }
}
