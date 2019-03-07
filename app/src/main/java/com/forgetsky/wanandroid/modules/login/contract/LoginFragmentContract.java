package com.forgetsky.wanandroid.modules.login.contract;

import com.forgetsky.wanandroid.base.presenter.IPresenter;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.core.event.RegisterEvent;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public interface LoginFragmentContract {
    interface View extends IView {
        void loginSuccess();
        void registerSuccess(RegisterEvent registerEvent);
    }

    interface Presenter extends IPresenter<View> {

        void login(String username, String password);
    }
}
