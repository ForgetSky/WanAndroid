package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;


public interface SearchResultContract {

    interface View extends IView {
        void showSearchResultList(ArticleListData articleListData, boolean isRefresh);

    }

    interface Presenter extends IPresenter<View> {
        void search(String k, boolean isShowError);
        void getSearchResultList( boolean isShowError);
        void loadMore();
    }
}
