package com.forgetsky.wanandroid.modules.homepager.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;

import java.util.List;

public interface HomePagerContract {
    interface View extends IView {
        /**
         * Show logout success
         */
        void showLogoutSuccess();
        void showArticleList(ArticleListData articleListData, boolean isRefresh);
        void showBannerData(List<BannerData> bannerDataList);
    }

    interface Presenter extends IPresenter<View> {

        void getArticleList(boolean isShowError);
        void getBannerData(boolean isShowError);

        void getHomePagerData(boolean isShowError);

        void refreshLayout(boolean isShowError);

        void loadMore();
        /**
         * Load more data
         */
        void loadMoreData();


    }
}
