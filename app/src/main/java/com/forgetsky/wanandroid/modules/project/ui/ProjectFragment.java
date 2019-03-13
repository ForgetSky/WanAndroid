package com.forgetsky.wanandroid.modules.project.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.project.bean.ProjectTreeData;
import com.forgetsky.wanandroid.modules.project.contract.ProjectContract;
import com.forgetsky.wanandroid.modules.project.presenter.ProjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    private static final String TAG = "ProjectFragment";
    @BindView(R.id.project_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;

    private List<ProjectTreeData> mProjectTreeData;
    private List<ProjectListFragment> mFragments = new ArrayList<>();

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
        for (ProjectTreeData data : mProjectTreeData) {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.PROJECT_CID, data.getId());
            ProjectListFragment projectListFragment = ProjectListFragment.newInstance(bundle);
            mFragments.add(projectListFragment);
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mProjectTreeData == null ? 0 : mProjectTreeData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mProjectTreeData.get(position).getName());
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
//                mViewPager.setCurrentItem(tab.getPosition(), false);
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
        if (mFragments.size() < 1) return;
        mFragments.get(mViewPager.getCurrentItem()).jumpToTheTop();
    }
}
