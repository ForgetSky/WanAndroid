/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.forgetsky.wanandroid.modules.login.presenter;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.presenter.BasePresenter;
import com.forgetsky.wanandroid.core.event.RegisterEvent;
import com.forgetsky.wanandroid.core.rx.BaseObserver;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.login.contract.RegisterFragmentContract;
import com.forgetsky.wanandroid.utils.RxUtils;
import org.simple.eventbus.EventBus;

import javax.inject.Inject;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class RegisterFragmentPresenter extends BasePresenter<RegisterFragmentContract.View> implements RegisterFragmentContract.Presenter{
    @Inject
    RegisterFragmentPresenter() {
    }

    @Override
    public void register(String username, String password, String password2) {
        addSubscribe(mDataManager.register(username, password, password2)
                .compose(RxUtils.SchedulerTransformer())
                .filter(loginData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        WanAndroidApp.getContext().getString(R.string.register_fail),
                        true) {
                    @Override
                    public void onSuccess(LoginData loginData) {
                        EventBus.getDefault().post(new RegisterEvent(loginData.getUsername(), loginData.getPassword()));
                        mView.registerSuccess();
                    }
                }));
    }
}
