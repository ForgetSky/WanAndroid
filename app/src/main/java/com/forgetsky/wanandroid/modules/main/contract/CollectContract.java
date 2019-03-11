package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;


public interface CollectContract {

    interface View extends CollectEventContract.View {
        void showCollectList(ArticleListData articleListData, boolean isRefresh);

    }

    interface Presenter extends CollectEventContract.Presenter<View> {
        void getCollectArticle(boolean isShowError);
        void loadMore();
        void getCollectList(boolean isShowError);
        void cancelCollectInCollectPage(int position, int id, int originId);
    }
}
