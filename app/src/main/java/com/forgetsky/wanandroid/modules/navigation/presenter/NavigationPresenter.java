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
    public void getNavigationListData() {
        addSubscribe(mDataManager.getNavigationListData()
                .compose(RxUtils.SchedulerTransformer())
                .filter( navigationListData-> mView != null)
                .subscribeWith(new BaseObserver<List<NavigationListData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_get_navigation_data),
                        true) {
                    @Override
                    public void onSuccess(List<NavigationListData> navigationListData) {
                        mView.showNavigationListData(navigationListData);
                    }
                }));
    }

    @Override
    public void reload() {
        getNavigationListData();
    }
}
