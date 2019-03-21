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

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;
import com.forgetsky.wanandroid.modules.main.contract.UsefulSitesContract;
import com.forgetsky.wanandroid.modules.main.presenter.UsefulSitesPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ForgetSky
 * @date 19-2-26
 */
public class UsefulSitesFragment extends BaseFragment<UsefulSitesPresenter> implements UsefulSitesContract.View {

    @BindView(R.id.useful_sites_flow_layout)
    TagFlowLayout mUsefulSitesFlowLayout;

    private List<UsefulSiteData> mUsefulSiteDataList;

    public static UsefulSitesFragment newInstance() {
        return new UsefulSitesFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_usefulsites;
    }

    @Override
    protected void initEventAndData() {
        mUsefulSiteDataList = new ArrayList<>();
        mPresenter.getUsefulSites();

    }

    @Override
    public void showUsefulSites(List<UsefulSiteData> usefulSiteData) {
        mUsefulSiteDataList = usefulSiteData;
        mUsefulSitesFlowLayout.setAdapter(new TagAdapter<UsefulSiteData>(usefulSiteData) {
            @Override
            public View getView(FlowLayout parent, int position, UsefulSiteData usefulSiteData) {
                TextView tv = (TextView) LayoutInflater.from(_mActivity)
                        .inflate(R.layout.flow_layout_tv, parent, false);
                if (usefulSiteData != null) {
                    tv.setText(usefulSiteData.getName());
                    tv.setTextColor(CommonUtils.getRandomColor());
                }
                return tv;
            }
        });
        mUsefulSitesFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
            CommonUtils.startArticleDetailActivity(_mActivity,
                    mUsefulSiteDataList.get(position1).getId(),
                    mUsefulSiteDataList.get(position1).getName().trim(),
                    mUsefulSiteDataList.get(position1).getLink().trim(),
                    false, false,
                    -1, Constants.TAG_DEFAULT);
            return true;
        });
    }
}
