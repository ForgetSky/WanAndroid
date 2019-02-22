package com.forgetsky.wanandroid.modules.homepager.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.homepager.contract.HomePagerContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import javax.inject.Inject;

public class HomePagerPresenter extends BasePresenter<HomePagerContract.View>
        implements HomePagerContract.Presenter {

    @Inject
    HomePagerPresenter() {
        super();
    }

    private int currentPage;
    private boolean isRefresh = true;


    @Override
    public void refreshLayout(boolean isShowError) {
        isRefresh = true;
        currentPage = 0;

        getArticleList(isShowError);

    }

    @Override
    public void getArticleList(boolean isShowError) {
        addSubscribe(mDataManager.getArticleList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showArticleList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getArticleList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        false) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showArticleList(articleListData, isRefresh);
                    }
                }));
    }


    @Override
    public void attachView(HomePagerContract.View view) {
        super.attachView(view);
    }
}
