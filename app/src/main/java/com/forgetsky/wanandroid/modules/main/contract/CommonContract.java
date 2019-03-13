package com.forgetsky.wanandroid.modules.main.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;


public interface CommonContract {

    interface View extends IView {
        
    }

    interface Presenter extends IPresenter<View> {

    }
}
