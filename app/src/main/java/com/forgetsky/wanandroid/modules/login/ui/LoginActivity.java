package com.forgetsky.wanandroid.modules.login.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.modules.login.contract.LoginContract;
import com.forgetsky.wanandroid.modules.main.presenter.MainPresenter;

import butterknife.BindView;

/**
 * @author: ForgetSky
 * @date: 2019/3/4
 */
public class LoginActivity extends BaseActivity<MainPresenter> implements LoginContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    @Override
    protected void initView() {
        loadMultipleRootFragment(R.id.login_frame_layout, 0,
                LoginFragment.newInstance(), RegisterFragment.newInstance());
        setToolbarTitle(R.string.login);
    }

    public void setToolbarTitle(int resId) {
        mTitle.setText(resId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {

    }
}
