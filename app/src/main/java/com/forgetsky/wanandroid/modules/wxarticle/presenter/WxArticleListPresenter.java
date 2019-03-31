/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
    public void refreshLayout(int id, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 1;
        this.id = id;
        getWxArticlesData(isShowStatusView);
    }

    @Override
    public void reload() {
        refreshLayout(id, true);
    }

    @Override
    public void getWxArticlesData(boolean isShowStatusView) {
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
        getWxArticlesData(false);
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
