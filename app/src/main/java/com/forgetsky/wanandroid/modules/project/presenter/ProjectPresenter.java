package com.forgetsky.wanandroid.modules.project.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.project.contract.ProjectContract;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View>
        implements ProjectContract.Presenter {

    @Inject
    ProjectPresenter() {
        super();
    }
    private int currentPage;

    @Override
    public void setCurrentPage(int page) {
        currentPage = page;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void attachView(ProjectContract.View view) {
        super.attachView(view);
    }
}
