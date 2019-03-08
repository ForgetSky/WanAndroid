package com.forgetsky.wanandroid.modules.main.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import javax.inject.Inject;


public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {

    private int currentPage;
    private boolean isRefresh = true;

    @Inject
    CollectPresenter() {
    }


    @Override
    public void getCollectArticle(boolean isShowError) {
        isRefresh = true;
        currentPage = 0;
        getCollectList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getCollectList(false);
    }

    @Override
    public void getCollectList(boolean isShowError) {
        addSubscribe(mDataManager.getCollectList(currentPage)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_collect_list),
                        isShowError) {
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
                        mView.cancelCollectSuccess(postion);
                    }
                }));
    }
}
