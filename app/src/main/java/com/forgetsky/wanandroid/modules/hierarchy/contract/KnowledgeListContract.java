package com.forgetsky.wanandroid.modules.hierarchy.contract;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;

public interface KnowledgeListContract {
    interface View extends CollectEventContract.View {
        void showKnowledgeListData(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getKnowledgeListData(int cid, boolean isShowStatusView);

        void loadMore(int cid);

    }
}
