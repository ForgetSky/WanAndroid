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
import com.forgetsky.wanandroid.modules.login.contract.RegisterFragmentContract;
import com.forgetsky.wanandroid.modules.login.presenter.RegisterFragmentPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: ForgetSky
 * @date: 2019/3/5
 */
public class RegisterFragment extends BaseFragment<RegisterFragmentPresenter> implements RegisterFragmentContract.View {
    @BindView(R.id.et_username)
    EditText mUsernameEdit;
    @BindView(R.id.et_password)
    EditText mPasswordEdit;
    @BindView(R.id.et_password2)
    EditText mPasswordEdit2;

    private AlertDialog mDialog;

    @OnClick({R.id.btn_register, R.id.tv_sign_in})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_sign_in:
                goToLogin();
                break;
            default:
                break;
        }
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    private void register() {
        String username = mUsernameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        String password2 = mPasswordEdit2.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showToast(_mActivity, getString(R.string.username_not_empty));
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(_mActivity, getString(R.string.password_not_empty));
        } else if (TextUtils.isEmpty(password2)) {
            ToastUtils.showToast(_mActivity, getString(R.string.re_password_not_empty));
        } else if (!TextUtils.equals(password, password2)) {
            ToastUtils.showToast(_mActivity, getString(R.string.twice_password_not_same));
        } else {
            mPresenter.register(username, password, password2);
        }
    }

    private void goToLogin() {
        LoginFragment loginFragment = findFragment(LoginFragment.class);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }
        getSupportDelegate().showHideFragment(loginFragment, RegisterFragment.this);
        LoginActivity loginActivity = (LoginActivity) _mActivity;
        loginActivity.setToolbarTitle(R.string.login);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void registerSuccess() {
        ToastUtils.showToast(_mActivity, getString(R.string.register_success));
        goToLogin();
    }

    @Override
    public void showLoading() {
        if (mDialog == null) {
            mDialog = CommonUtils.getLoadingDialog(_mActivity, getString(R.string.registering));
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
