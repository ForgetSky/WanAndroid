package com.forgetsky.wanandroid.modules.wxarticle.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.CollectEvent;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.presenter.CollectEventPresenter;
import com.forgetsky.wanandroid.modules.wxarticle.contract.WxArticleListContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class WxArticleListPresenter extends CollectEventPresenter<WxArticleListContract.View>
        implements WxArticleListContract.Presenter {

    @Inject
    WxArticleListPresenter() {
    }

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int id;

    @Override
    public void reload() {
        getWxArticlesData(id, true);
    }

    @Override
    public void getWxArticlesData(int id, boolean isShowStatusView) {
        this.id = id;
        addSubscribe(mDataManager.getWxArticlesData(id, currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        if(isShowStatusView && currentPage == 1 &&
                                articleListData.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showWxArticlesData(articleListData, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void getWxSearchData(int id, String k, boolean isShowStatusView) {
        addSubscribe(mDataManager.getWxSearchData(id, currentPage, k)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        if(isShowStatusView && currentPage == 1 &&
                                articleListData.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showWxArticlesData(articleListData, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getWxArticlesData(id,false);
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = Constants.WX_PAGER)
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            mView.showCancelCollectSuccess(collectEvent.getArticlePostion());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePostion());
        }
    }
}
