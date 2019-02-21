package com.forgetsky.wanandroid.modules.homepager.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;

public interface HomePagerContract {
    interface View extends IView {
        /**
         * Show logout success
         */
        void showLogoutSuccess();
        void showArticleList(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends IPresenter<View> {

        void getArticleList(boolean isShowError);

        void refreshLayout(boolean isShowError);

        void loadMore();
        /**
         * Load more data
         */
        void loadMoreData();


    }
}
