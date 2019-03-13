package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;


public interface SearchResultContract {

    interface View extends CollectEventContract.View {
        void showSearchResultList(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {
        void search(String k, boolean isShowStatusView);
        void getSearchResultList( boolean isShowStatusView);
        void loadMore();
    }
}
