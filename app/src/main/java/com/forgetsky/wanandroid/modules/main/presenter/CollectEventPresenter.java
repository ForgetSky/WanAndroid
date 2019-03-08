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
