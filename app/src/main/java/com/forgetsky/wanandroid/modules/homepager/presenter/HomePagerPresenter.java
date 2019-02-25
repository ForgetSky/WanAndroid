package com.forgetsky.wanandroid.modules.homepager.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.homepager.contract.HomePagerContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
        getHomePagerData(isShowError);
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
    public void getBannerData(boolean isShowError) {
        addSubscribe(mDataManager.getBannerData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onSuccess(List<BannerData> bannerData) {
                        mView.showBannerData(bannerData);
                    }
                }));
    }

    @Override
    public void getHomePagerData(boolean isShowError) {
        getBannerData(isShowError);
        addSubscribe(Observable.zip(mDataManager.getTopArticles(), mDataManager.getArticleList(0),
                (topArticlesBaseResponse, articleListDataBaseResponse) -> {
                    articleListDataBaseResponse.getData().getDatas().
                            addAll(0, topArticlesBaseResponse.getData());
                    return articleListDataBaseResponse;
                })
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
