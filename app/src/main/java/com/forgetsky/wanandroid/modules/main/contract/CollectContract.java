package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;


public interface CollectContract {

    interface View extends IView {
        void showCollectList(ArticleListData articleListData, boolean isRefresh);
        void cancelCollectSuccess(int position);

    }

    interface Presenter extends IPresenter<View> {
        void getCollectArticle(boolean isShowError);
        void loadMore();
        void getCollectList(boolean isShowError);
        void cancelCollectInCollectPage(int position, int id, int originId);
    }
}
