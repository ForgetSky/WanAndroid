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

package com.forgetsky.wanandroid.modules.main.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.main.contract.CommonContract;
import com.forgetsky.wanandroid.modules.main.presenter.CommonPresenter;
import com.forgetsky.wanandroid.modules.main.ui.fragment.AboutFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.CollectFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.SearchResultFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.SettingFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.UsefulSitesFragment;

import butterknife.BindView;

/**
 * @author ForgetSky
 * @date 19-2-25
 */
public class CommonActivity extends BaseActivity<CommonPresenter> implements CommonContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.common_frame_layout)
    FrameLayout mFrameGroup;
    Fragment mTargetFragment;

    @Override
    protected void initView() {
        int fragType = getIntent().getIntExtra(Constants.TYPE_FRAGMENT_KEY, -1);
        Bundle extras = getIntent().getExtras();
        String title = "";
        switch (fragType) {
            case Constants.TYPE_USEFULSITES:
                mTargetFragment = UsefulSitesFragment.newInstance();
                title = getString(R.string.useful_sites);
                break;
            case Constants.TYPE_SEARCH_RESULT:
                mTargetFragment = SearchResultFragment.newInstance(extras);
                assert extras != null;
                title = extras.getString(Constants.SEARCH_KEY, "");
                break;
            case Constants.TYPE_COLLECT:
                mTargetFragment = CollectFragment.newInstance();
                title = getString(R.string.collect);
                break;
            case Constants.TYPE_SETTING:
                mTargetFragment = SettingFragment.newInstance();
                title = getString(R.string.setting);
                break;
            case Constants.TYPE_ABOUT_US:
                mTargetFragment = AboutFragment.newInstance();
                title = getString(R.string.about_us);
                break;
            default:
                break;
        }
        if (mTargetFragment == null) {
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.common_frame_layout, mTargetFragment)
                    .commit();

            mTitle.setText(title);

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common;
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
