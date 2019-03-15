package com.forgetsky.wanandroid.modules.hierarchy.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.hierarchy.bean.KnowledgeTreeData;
import com.forgetsky.wanandroid.modules.hierarchy.contract.KnowledgeActivityContract;
import com.forgetsky.wanandroid.modules.hierarchy.presenter.KnowledgeActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KnowledgeActivity extends BaseActivity<KnowledgeActivityPresenter> implements KnowledgeActivityContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.knowledge_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.knowledge_viewpager)
    ViewPager mViewPager;

    private List<KnowledgeTreeData> mKnowledgeTreeDataList;
    private SparseArray<KnowledgeListFragment> fragmentSparseArray = new SparseArray<>();
    private KnowledgeListFragment currentFragment;
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_hierarchy;
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
        KnowledgeTreeData knowledgeTreeData = (KnowledgeTreeData) getIntent().getSerializableExtra(Constants.KNOWLEDGE_DATA);
        if (knowledgeTreeData == null || knowledgeTreeData.getName() == null) {
            return;
        }
        mTitle.setText(knowledgeTreeData.getName().trim());
        mKnowledgeTreeDataList = knowledgeTreeData.getChildren();
        if (mKnowledgeTreeDataList == null) {
            return;
        }
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                KnowledgeListFragment knowledgeListFragment = fragmentSparseArray.get(position);
                if (knowledgeListFragment != null) {
                    return knowledgeListFragment;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.KNOWLEDGE_CID, mKnowledgeTreeDataList.get(position).getId());
                    knowledgeListFragment = KnowledgeListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position, knowledgeListFragment);
                    return knowledgeListFragment;
                }
            }

            @Override
            public int getCount() {
                return mKnowledgeTreeDataList == null ? 0 : mKnowledgeTreeDataList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mKnowledgeTreeDataList.get(position).getName());
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

    @OnClick({R.id.knowledge_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.knowledge_floating_action_btn:
                jumpToTheTop();
                break;
            default:
                break;
        }
    }
    public void jumpToTheTop() {
        currentFragment = fragmentSparseArray.get(mViewPager.getCurrentItem());
        if (currentFragment != null) {
            currentFragment.jumpToTheTop();
        }
    }

    @Override
    public void onDestroy() {
        fragmentSparseArray.clear();
        fragmentSparseArray = null;
        mKnowledgeTreeDataList.clear();
        mKnowledgeTreeDataList = null;
        super.onDestroy();
    }
}
