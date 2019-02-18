package com.forgetsky.wanandroid.modules.hierarchy.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.hierarchy.contract.KnowledgeHierarchyContract;

import javax.inject.Inject;

public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View>
        implements KnowledgeHierarchyContract.Presenter {

    @Inject
    KnowledgeHierarchyPresenter() {
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
    public void attachView(KnowledgeHierarchyContract.View view) {
        super.attachView(view);
    }
}
