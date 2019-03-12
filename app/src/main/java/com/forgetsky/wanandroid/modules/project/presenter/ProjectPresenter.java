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
    public void getProjectTreeData(boolean isShowError) {
        addSubscribe(mDataManager.getProjectTreeData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(projectTreeDataList -> mView != null)
                .subscribeWith(new BaseObserver<List<ProjectTreeData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onSuccess(List<ProjectTreeData> projectTreeDataList) {
                        mView.showProjectTreeData(projectTreeDataList);
                    }
                }));
    }
}
