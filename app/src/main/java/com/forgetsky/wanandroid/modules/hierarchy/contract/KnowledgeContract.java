package com.forgetsky.wanandroid.modules.hierarchy.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.hierarchy.bean.KnowledgeTreeData;

import java.util.List;

public interface KnowledgeContract {
    interface View extends IView {
        void showKnowledgeTreeData(List<KnowledgeTreeData> knowledgeTreeData);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowledgeTreeData();
    }
}
