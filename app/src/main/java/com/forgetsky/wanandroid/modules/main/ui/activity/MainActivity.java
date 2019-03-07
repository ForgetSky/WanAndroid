package com.forgetsky.wanandroid.modules.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.hierarchy.ui.KnowledgeHierarchyFragment;
import com.forgetsky.wanandroid.modules.homepager.ui.HomePagerFragment;
import com.forgetsky.wanandroid.modules.login.ui.LoginActivity;
import com.forgetsky.wanandroid.modules.main.contract.MainContract;
import com.forgetsky.wanandroid.modules.main.presenter.MainPresenter;
import com.forgetsky.wanandroid.modules.navigation.ui.NavigationFragment;
import com.forgetsky.wanandroid.modules.project.ui.ProjectFragment;
import com.forgetsky.wanandroid.modules.wxarticle.ui.WxArticleFragment;
import com.forgetsky.wanandroid.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.fragment_group)
    FrameLayout mFrameGroup;
    TextView mUsTv;

    //fragments
    private HomePagerFragment mHomePagerFragment;
    private KnowledgeHierarchyFragment mKnowledgeHierarchyFragment;
    private NavigationFragment mNavigationFragment;
    private WxArticleFragment mWxArticleFragment;
    private ProjectFragment mProjectFragment;
    private int mLastFgIndex = -1;
    private int mCurrentFgIndex = 0;
    private long clickTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentFgIndex = savedInstanceState.getInt(Constants.CURRENT_FRAGMENT_KEY);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.CURRENT_FRAGMENT_KEY, mCurrentFgIndex);
    }

    @Override
    protected void initView() {
        initDrawerLayout();
        showFragment(mCurrentFgIndex);
        initNavigationView();
        initBottomNavigationView();
    }

    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index) {
            case Constants.TYPE_HOME_PAGER:
                if (mHomePagerFragment == null) {
                    mHomePagerFragment = HomePagerFragment.newInstance();
                    transaction.add(R.id.fragment_group, mHomePagerFragment);
                }
                transaction.show(mHomePagerFragment);
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (mKnowledgeHierarchyFragment == null) {
                    mKnowledgeHierarchyFragment = KnowledgeHierarchyFragment.newInstance();
                    transaction.add(R.id.fragment_group, mKnowledgeHierarchyFragment);
                }
                transaction.show(mKnowledgeHierarchyFragment);
                break;
            case Constants.TYPE_NAVIGATION:
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.newInstance();
                    transaction.add(R.id.fragment_group, mNavigationFragment);
                }
                transaction.show(mNavigationFragment);
                break;
            case Constants.TYPE_WX_ARTICLE:
                if (mWxArticleFragment == null) {
                    mWxArticleFragment = WxArticleFragment.newInstance();
                    transaction.add(R.id.fragment_group, mWxArticleFragment);
                }
                transaction.show(mWxArticleFragment);
                break;
            case Constants.TYPE_PROJECT:
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.newInstance();
                    transaction.add(R.id.fragment_group, mProjectFragment);
                }
                transaction.show(mProjectFragment);
                break;

            default:

                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex) {
            case Constants.TYPE_HOME_PAGER:
                if (mHomePagerFragment != null) {
                    transaction.hide(mHomePagerFragment);
                }
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (mKnowledgeHierarchyFragment != null) {
                    transaction.hide(mKnowledgeHierarchyFragment);
                }
                break;
            case Constants.TYPE_NAVIGATION:
                if (mNavigationFragment != null) {
                    transaction.hide(mNavigationFragment);
                }
                break;
            case Constants.TYPE_WX_ARTICLE:
                if (mWxArticleFragment != null) {
                    transaction.hide(mWxArticleFragment);
                }
                break;
            case Constants.TYPE_PROJECT:
                if (mProjectFragment != null) {
                    transaction.hide(mProjectFragment);
                }
                break;
            default:
                break;
        }
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    loadPager(getString(R.string.home_pager), Constants.TYPE_HOME_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    loadPager(getString(R.string.knowledge_hierarchy), Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_navigation:
                    loadPager(getString(R.string.navigation), Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_wx_article:
                    loadPager(getString(R.string.wx_article), Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_project:
                    loadPager(getString(R.string.project), Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void loadPager(String title, int index) {
        mTitle.setText(title);
        showFragment(index);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.home_pager);
        }
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //TODO navigation item
                    case R.id.nav_item_my_collect:
                        Toast.makeText(MainActivity.this, "you click nav_item_my_collect", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_todo:
                        break;
                    case R.id.nav_item_night_mode:
                        break;
                    case R.id.nav_item_setting:
                        break;
                    case R.id.nav_item_about_us:
                        break;
                    case R.id.nav_item_logout:
                        mPresenter.logout();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login);
        mUsTv.setText(mPresenter.getLoginStatus() ? mPresenter.getLoginAccount() : getString(R.string.login));
        mUsTv.setOnClickListener(v -> startLoginActivity());
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(mPresenter.getLoginStatus());
    }

    private void startLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_usage:
                intent = new Intent(MainActivity.this, CommonActivity.class);
                intent.putExtra(Constants.TYPE_FRAGMENT_KEY, Constants.TYPE_USEFULSITES);
                startActivity(intent);
                break;
            case R.id.action_search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick({R.id.main_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                jumpToTheTop();
                break;
            default:
                break;
        }
    }

    private void jumpToTheTop() {
        switch (mCurrentFgIndex) {
            case Constants.TYPE_HOME_PAGER:
                if (mHomePagerFragment != null) {
                    mHomePagerFragment.jumpToTheTop();
                }
                break;
//            case Constants.TYPE_KNOWLEDGE:
//                if (mKnowledgeHierarchyFragment != null) {
//                    mKnowledgeHierarchyFragment.jumpToTheTop();
//                }
//                break;
//            case Constants.TYPE_WX_ARTICLE:
//                if (mWxArticleFragment != null) {
//                    mWxArticleFragment.jumpToTheTop();
//                }
//            case Constants.TYPE_NAVIGATION:
//                if (mNavigationFragment != null) {
//                    mNavigationFragment.jumpToTheTop();
//                }
//                break;
//            case Constants.TYPE_PROJECT:
//                if (mProjectFragment != null) {
//                    mProjectFragment.jumpToTheTop();
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }

    /**
     * 处理回退事件
     */
    @Override
    public void onBackPressedSupport() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - clickTime) > Constants.DOUBLE_INTERVAL_TIME) {
                ToastUtils.showToast(MainActivity.this, getString(R.string.double_click_exit_toast));
                clickTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }

    @Override
    public void handleLoginSuccess() {
        mUsTv.setText(mPresenter.getLoginAccount());
        mUsTv.setOnClickListener(null);
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(true);
    }

    @Override
    public void handleLogoutSuccess() {
        mUsTv.setText(getString(R.string.login));
        mUsTv.setOnClickListener(v -> startLoginActivity());
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
    }
}
