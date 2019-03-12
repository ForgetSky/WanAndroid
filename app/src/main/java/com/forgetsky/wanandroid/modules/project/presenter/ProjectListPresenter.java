package com.forgetsky.wanandroid.modules.project.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.CollectEvent;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.presenter.CollectEventPresenter;
import com.forgetsky.wanandroid.modules.project.contract.ProjectListContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class ProjectListPresenter extends CollectEventPresenter<ProjectListContract.View>
        implements ProjectListContract.Presenter {

    @Inject
    ProjectListPresenter() {
    }

    private int currentPage = 1;
    private boolean isRefresh = true;

    @Override
    public void getProjectListData(int cid, boolean isShowError) {
        addSubscribe(mDataManager.getProjectListData(currentPage, cid)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onSuccess(ArticleListData articleListData) {
                        mView.showProjectListData(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMore(int cid) {
        isRefresh = false;
        currentPage++;
        getProjectListData(cid, false);
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

//    @Subscriber()
//    public void loginSuccessEvent(LoginEvent loginEvent) {
//        getHomePagerData(false);
//    }
//
//    @Subscriber()
//    public void logoutSuccessEvent(LogoutEvent logoutEvent) {
//        getHomePagerData(false);
//    }
//
//    @Subscriber()
//    public void refreshHomeEvent(RefreshHomeEvent refreshHomeEvent) {
//        getHomePagerData(false);
//    }

    @Subscriber(tag = Constants.PROJECT_PAGER)
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            mView.showCancelCollectSuccess(collectEvent.getArticlePostion());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePostion());
        }
    }
}
