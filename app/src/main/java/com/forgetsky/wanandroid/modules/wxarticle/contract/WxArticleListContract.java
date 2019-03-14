package com.forgetsky.wanandroid.modules.wxarticle.contract;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;

public interface WxArticleListContract {
    interface View extends CollectEventContract.View {
        void showWxArticlesData(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getWxArticlesData(int id, boolean isShowStatusView);

        void getWxSearchData(int id, String k, boolean isShowStatusView);

        void loadMore();
    }
}
