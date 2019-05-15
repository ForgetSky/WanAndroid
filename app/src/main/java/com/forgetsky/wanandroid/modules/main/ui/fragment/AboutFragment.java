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

package com.forgetsky.wanandroid.modules.main.ui.fragment;

import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.main.contract.AboutContract;
import com.forgetsky.wanandroid.modules.main.presenter.AboutPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.tencent.bugly.beta.Beta;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ForgetSky
 * @date 19-2-26
 */
public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutContract.View {

    @BindView(R.id.about_version)
    TextView mAboutVersion;


    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initEventAndData() {
        showAboutContent();
    }

    private void showAboutContent() {
        try {
            String versionStr = getString(R.string.app_name)
                    + " V" + WanAndroidApp.getContext().getPackageManager()
                    .getPackageInfo(WanAndroidApp.getContext().getPackageName(), 0).versionName;
            mAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.about_upgrade, R.id.about_website, R.id.about_source_code,
            R.id.about_feedback, R.id.about_copyright})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_upgrade:
                Beta.checkUpgrade();
                break;
            case R.id.about_website:
                CommonUtils.startArticleDetailActivity(_mActivity, -1,
                       getString(R.string.about_website), Constants.ABOUT_WEBSITE,
                        false, false, -1, Constants.TAG_DEFAULT);
                break;
            case R.id.about_source_code:
                CommonUtils.startArticleDetailActivity(_mActivity, -1,
                        getString(R.string.about_source_code), Constants.ABOUT_SOURCE,
                        false, false, -1, Constants.TAG_DEFAULT);
                break;
            case R.id.about_feedback:
                CommonUtils.startArticleDetailActivity(_mActivity, -1,
                        getString(R.string.about_feedback), Constants.ABOUT_FEEDBACK,
                        false, false, -1, Constants.TAG_DEFAULT);
                break;
            case R.id.about_copyright:
                new AlertDialog.Builder(_mActivity)
                        .setTitle(R.string.about_copyright)
                    .setMessage(R.string.copyright_content)
                    .setCancelable(true)
                    .show();
                break;
            default:
                break;
        }
    }

}
