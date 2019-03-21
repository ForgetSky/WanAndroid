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

package com.forgetsky.wanandroid.modules.project.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.project.bean.ProjectTreeData;
import com.forgetsky.wanandroid.modules.project.contract.ProjectContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View>
        implements ProjectContract.Presenter {

    @Inject
    ProjectPresenter() {
    }

    @Override
    public void getProjectTreeData() {
        addSubscribe(mDataManager.getProjectTreeData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(projectTreeDataList -> mView != null)
                .subscribeWith(new BaseObserver<List<ProjectTreeData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_get_project_data),
                        true) {
                    @Override
                    public void onSuccess(List<ProjectTreeData> projectTreeDataList) {
                        mView.showProjectTreeData(projectTreeDataList);
                    }
                }));
    }

    @Override
    public void reload() {
        getProjectTreeData();
    }
}
