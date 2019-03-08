package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;


public interface CollectEventContract {

    interface View extends IView {
        void showCollectSuccess(int position);
        void showCancelCollectSuccess(int position);
    }

    interface Presenter<V extends View> extends IPresenter<V> {
        void addCollectArticle(int postion, int id);
        void cancelCollectArticle(int postion, int id);
    }
}
