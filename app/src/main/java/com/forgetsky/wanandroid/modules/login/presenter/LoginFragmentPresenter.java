package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.login.contract.LoginFragmentContract;
import com.forgetsky.wanandroid.utils.RxUtils;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginFragmentPresenter extends BasePresenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter{
    @Inject
    LoginFragmentPresenter() {
    }

    @Override
    public void login(String username, String password) {
        addSubscribe(mDataManager.login(username, password)
                .compose(RxUtils.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getContext().getString(R.string.login_fail),
                        false) {
                    @Override
                    public void onSuccess(LoginData loginData) {
                        mView.loginSuccess();
                    }
                }));

    }
}
