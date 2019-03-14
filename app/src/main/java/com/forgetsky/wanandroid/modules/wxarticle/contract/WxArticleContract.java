package com.forgetsky.wanandroid.modules.wxarticle.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.modules.wxarticle.bean.WxChapterData;

import java.util.List;

public interface WxArticleContract {
    interface View extends IView {
        void showWxChapterListData(List<WxChapterData> wxChapterDataList);
    }

    interface Presenter extends IPresenter<View> {
        void getWxChapterListData();

    }
}
