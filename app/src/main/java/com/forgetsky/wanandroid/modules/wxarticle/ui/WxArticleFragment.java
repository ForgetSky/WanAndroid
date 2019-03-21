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

package com.forgetsky.wanandroid.modules.wxarticle.ui;

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
import com.forgetsky.wanandroid.modules.wxarticle.bean.WxChapterData;
import com.forgetsky.wanandroid.modules.wxarticle.contract.WxArticleContract;
import com.forgetsky.wanandroid.modules.wxarticle.presenter.WxArticlePresenter;

import java.util.List;

import butterknife.BindView;

public class WxArticleFragment extends BaseFragment<WxArticlePresenter> implements WxArticleContract.View {

    private static final String TAG = "WxArticleFragment";
    @BindView(R.id.wx_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.wx_viewpager)
    ViewPager mViewPager;

    private List<WxChapterData> mWxChapterDataList;
    private SparseArray<WxArticleListFragment> fragmentSparseArray = new SparseArray<>();
    private WxArticleListFragment currentFragment;

    public static WxArticleFragment newInstance() {
        return new WxArticleFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWxChapterListData();
    }

    @Override
    public void showWxChapterListData(List<WxChapterData> wxChapterDataList) {
        mWxChapterDataList = wxChapterDataList;
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                WxArticleListFragment wxArticleListFragment = fragmentSparseArray.get(position);
                if (wxArticleListFragment != null) {
                    return wxArticleListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.WX_CHAPTER_ID, mWxChapterDataList.get(position).getId());
                    wxArticleListFragment = WxArticleListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position, wxArticleListFragment);
                    return wxArticleListFragment;
                }
            }

            @Override
            public int getCount() {
                return mWxChapterDataList == null ? 0 : mWxChapterDataList.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mWxChapterDataList.get(position).getName());
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
        if (mWxChapterDataList != null) {
            mWxChapterDataList.clear();
            mWxChapterDataList = null;
        }
        super.onDestroyView();
    }
}
