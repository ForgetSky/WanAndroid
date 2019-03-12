package com.forgetsky.wanandroid.modules.project.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.project.bean.ProjectTreeData;

import java.util.List;

public interface ProjectContract {
    interface View extends IView {
        void showProjectTreeData(List<ProjectTreeData> projectTreeDataList);
    }

    interface Presenter extends IPresenter<View> {
        void getProjectTreeData(boolean isShowError);
    }
}
