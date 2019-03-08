package com.forgetsky.wanandroid.modules.homepager.contract;

import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;

import java.util.List;

public interface HomePagerContract {

    interface View extends CollectEventContract.View {
        void showArticleList(ArticleListData articleListData, boolean isRefresh);
        void showBannerData(List<BannerData> bannerDataList);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getArticleList(boolean isShowError);
        void getBannerData(boolean isShowError);

        void getHomePagerData(boolean isShowError);

        void refreshLayout(boolean isShowError);

        void loadMore();

    }
}
