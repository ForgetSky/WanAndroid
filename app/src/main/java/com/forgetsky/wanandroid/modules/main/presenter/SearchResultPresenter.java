package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.SearchResultContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import javax.inject.Inject;


public class SearchResultPresenter extends CollectEventPresenter<SearchResultContract.View> implements SearchResultContract.Presenter {

    @Inject
    SearchResultPresenter() {
    }

    private int currentPage;
    private boolean isRefresh = true;
    private String searchKey;


    @Override
    public void search(String k, boolean isShowError) {
        isRefresh = true;
        currentPage = 0;
        searchKey = k;
        getSearchResultList(isShowError);
    }

    @Override
    public void getSearchResultList(boolean isShowError) {
        addSubscribe(mDataManager.getSearchResultList(currentPage, searchKey)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showSearchResultList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getSearchResultList(false);
    }
}
