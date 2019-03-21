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

package com.forgetsky.wanandroid.modules.login.ui;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.event.RegisterEvent;
import com.forgetsky.wanandroid.modules.login.contract.LoginFragmentContract;
import com.forgetsky.wanandroid.modules.login.presenter.LoginFragmentPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: ForgetSky
 * @date: 2019/3/5
 */
public class LoginFragment extends BaseFragment<LoginFragmentPresenter> implements LoginFragmentContract.View {

    @BindView(R.id.et_username)
    EditText mUsernameEdit;
    @BindView(R.id.et_password)
    EditText mPasswordEdit;

    private AlertDialog mDialog;

    @OnClick({R.id.btn_login, R.id.tv_sign_up})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_sign_up:
                goToRegister();
                break;
            default:
                break;
        }
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData() {

    }

    private void login() {
        String username = mUsernameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showToast(_mActivity, getString(R.string.username_not_empty));
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(_mActivity, getString(R.string.password_not_empty));
        } else {
            mPresenter.login(username, password);
        }
    }

    private void goToRegister() {
        RegisterFragment registerFragment = findFragment(RegisterFragment.class);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
        }
        getSupportDelegate().showHideFragment(registerFragment, LoginFragment.this);
        LoginActivity loginActivity = (LoginActivity) _mActivity;
        loginActivity.setToolbarTitle(R.string.register);
    }

    @Override
    public void loginSuccess() {
        ToastUtils.showToast(_mActivity, getString(R.string.login_success));
        _mActivity.finish();
    }

    @Override
    public void registerSuccess(RegisterEvent registerEvent) {
        mUsernameEdit.setText(registerEvent.getUsername());
        mPasswordEdit.setText(registerEvent.getPassword());
    }

    @Override
    public void showLoading() {
        if (mDialog == null) {
            mDialog = CommonUtils.getLoadingDialog(_mActivity, getString(R.string.logging_in));
        }
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
