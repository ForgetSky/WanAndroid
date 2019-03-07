package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.event.LoginEvent;
import com.forgetsky.wanandroid.core.event.RegisterEvent;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.login.contract.LoginFragmentContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginFragmentPresenter extends BasePresenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter {
    @Inject
    LoginFragmentPresenter() {
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void login(String username, String password) {
        addSubscribe(mDataManager.login(username, password)
                .compose(RxUtils.SchedulerTransformer())
                .filter(loginData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getContext().getString(R.string.login_fail),
                        false) {
                    @Override
                    public void onSuccess(LoginData loginData) {
                        setLoginStatus(true);
                        setLoginAccount(loginData.getUsername());
                        EventBus.getDefault().post(new LoginEvent());
                        mView.loginSuccess();
                    }
                }));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RegisterSuccessEvent(RegisterEvent registerEvent) {
        mView.registerSuccess(registerEvent);
    }
}
