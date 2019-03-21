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

package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.RefreshHomeEvent;
import com.forgetsky.wanandroid.core.event.CollectEvent;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;


public class CollectPresenter extends CollectEventPresenter<CollectContract.View> implements CollectContract.Presenter {

    private int currentPage;
    private boolean isRefresh = true;
    private boolean isReCollected = false;

    @Inject
    CollectPresenter() {
    }

    @Override
    public void getCollectArticle(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getCollectList(isShowStatusView);
    }

    @Override
    public void reload() {
        getCollectArticle(true);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getCollectList(false);
    }

    @Override
    public void getCollectList(boolean isShowStatusView) {
        addSubscribe(mDataManager.getCollectList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_collect_list),
                        isShowStatusView) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showCollectList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void cancelCollectInCollectPage(int postion, int id, int originId) {
        addSubscribe(mDataManager.cancelCollectInCollectPage(id, originId)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_cancel_collect),
                        false) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showCancelCollectSuccess(postion);
                        EventBus.getDefault().post(new RefreshHomeEvent());
                    }
                }));
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = Constants.COLLECT_PAGER)
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            if (isReCollected) {
                isReCollected = false;
                mView.showCancelCollectSuccess(0);
            } else {
                mView.showCancelCollectSuccess(collectEvent.getArticlePostion());
            }
        } else {
            getCollectList(false);
            mView.showCollectSuccess(-1);
            isReCollected = true;
        }
        EventBus.getDefault().post(new RefreshHomeEvent());
    }
}
