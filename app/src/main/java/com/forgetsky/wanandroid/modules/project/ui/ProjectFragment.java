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

package com.forgetsky.wanandroid.modules.project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.project.bean.ProjectTreeData;
import com.forgetsky.wanandroid.modules.project.contract.ProjectContract;
import com.forgetsky.wanandroid.modules.project.presenter.ProjectPresenter;

import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    private static final String TAG = "ProjectFragment";
    @BindView(R.id.project_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

    private List<ProjectTreeData> mProjectTreeData;
    private SparseArray<ProjectListFragment> fragmentSparseArray = new SparseArray<>();
    private ProjectListFragment currentFragment;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectTreeData();
    }

    @Override
    public void showProjectTreeData(List<ProjectTreeData> projectTreeDataList) {
        mProjectTreeData = projectTreeDataList;
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ProjectListFragment projectListFragment = fragmentSparseArray.get(position);
                if (projectListFragment != null) {
                    return projectListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.PROJECT_CID, mProjectTreeData.get(position).getId());
                    projectListFragment = ProjectListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position, projectListFragment);
                    return projectListFragment;
                }
            }

            @Override
            public int getCount() {
                return mProjectTreeData == null ? 0 : mProjectTreeData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mProjectTreeData.get(position).getName());
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void jumpToTheTop() {
        currentFragment = fragmentSparseArray.get(mViewPager.getCurrentItem());
        if (currentFragment != null) {
            currentFragment.jumpToTheTop();
        }
    }

    @Override
    public void onDestroyView() {
        if (fragmentSparseArray != null) {
            fragmentSparseArray.clear();
            fragmentSparseArray = null;
        }
        if (mProjectTreeData != null) {
            mProjectTreeData.clear();
            mProjectTreeData = null;
        }
        super.onDestroyView();
    }
}
