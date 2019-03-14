package com.forgetsky.wanandroid.modules.wxarticle.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.wxarticle.bean.WxChapterData;
import com.forgetsky.wanandroid.modules.wxarticle.contract.WxArticleContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class WxArticlePresenter extends BasePresenter<WxArticleContract.View>
        implements WxArticleContract.Presenter {

    @Inject
    WxArticlePresenter() {
    }

    @Override
    public void getWxChapterListData() {
        addSubscribe(mDataManager.getWxChapterListData()
                .compose(RxUtils.SchedulerTransformer())
                .filter(wxChapterDataList -> mView != null)
                .subscribeWith(new BaseObserver<List<WxChapterData>>(mView,
                        WanAndroidApp.getContext().getString(R.string.failed_to_get_wx_chapters_data),
                        true) {
                    @Override
                    public void onSuccess(List<WxChapterData> wxChapterDataList) {
                        mView.showWxChapterListData(wxChapterDataList);
                    }
                }));
    }

    @Override
    public void reload() {
        getWxChapterListData();
    }
}
