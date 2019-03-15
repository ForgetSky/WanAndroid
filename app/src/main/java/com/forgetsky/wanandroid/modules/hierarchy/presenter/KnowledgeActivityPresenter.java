package com.forgetsky.wanandroid.modules.hierarchy.presenter;

import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.modules.hierarchy.contract.KnowledgeActivityContract;

import javax.inject.Inject;


public class KnowledgeActivityPresenter extends BasePresenter<KnowledgeActivityContract.View> implements KnowledgeActivityContract.Presenter {

    @Inject
    KnowledgeActivityPresenter() {
    }

}
