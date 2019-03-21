/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
