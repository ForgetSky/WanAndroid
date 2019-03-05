package com.forgetsky.wanandroid.modules.login.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public interface RegisterFragmentContract {
    interface View extends IView {
        void registerSuccess();
    }

    interface Presenter extends IPresenter<View> {
        void register(String username, String password, String password2);
    }
}
