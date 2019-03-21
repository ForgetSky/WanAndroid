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
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;
import com.forgetsky.wanandroid.utils.RxUtils;


public class CollectEventPresenter<V extends CollectEventContract.View>
        extends BasePresenter<V> implements CollectEventContract.Presenter<V> {


    @Override
    public void addCollectArticle(int postion, int id) {
        addSubscribe(mDataManager.addCollectArticle(id)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_cancel_collect),
                        false) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showCollectSuccess(postion);
                    }
                }));
    }

    @Override
    public void cancelCollectArticle(int postion, int id) {
        addSubscribe(mDataManager.cancelCollectArticle(id)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_cancel_collect),
                        false) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showCancelCollectSuccess(postion);
                    }
                }));
    }
}
