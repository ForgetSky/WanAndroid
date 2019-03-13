package com.forgetsky.wanandroid.modules.project.contract;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;

public interface ProjectListContract {

    interface View extends CollectEventContract.View {
        void showProjectListData(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getProjectListData(int cid, boolean isShowStatusView);

        void loadMore(int cid);

    }
}
