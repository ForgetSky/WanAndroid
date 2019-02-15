package com.forgetsky.wanandroid.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.homepager.ui.HomePagerFragment;
import com.forgetsky.wanandroid.main.contract.MainContract;
import com.forgetsky.wanandroid.main.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavigationView();
        HomePagerFragment targetFg = findFragment(HomePagerFragment.class);
        if (targetFg == null) {
            targetFg = HomePagerFragment.getInstance();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();

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
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.home_pager);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    private void initNavigationView() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //TODO navigation item
                    case R.id.nav_item_my_collect:
                        Toast.makeText(MainActivity.this,"you click nav_item_my_collect", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_about_us:
                        break;
                    case R.id.nav_item_logout:
                        break;
                    case R.id.nav_item_setting:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //TODO toolbar button
            case R.id.action_usage:
                Toast.makeText(this,"you click usage", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Toast.makeText(this,"you click search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showLogoutSuccess() {

    }
}
