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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.event.CollectEvent;
import com.forgetsky.wanandroid.modules.main.contract.ArticleDetailContract;
import com.forgetsky.wanandroid.modules.main.presenter.ArticleDetailPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.simple.eventbus.EventBus;

import java.lang.reflect.Method;

import butterknife.BindView;


public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {

    @BindView(R.id.content_layout)
    CoordinatorLayout mContent;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    private int articleId;
    private String articleLink;
    private String title;
    private boolean isCollected;
    private boolean isShowCollectIcon;
    private int articleItemPosition;
    private String eventBusTag;
    private MenuItem mCollectItem;

    private AgentWeb mAgentWeb;

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        getBundleData();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(Html.fromHtml(title));
            mTitle.setSelected(true);
        }

        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(Html.fromHtml(title));
            }
        };

        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        NestedScrollAgentWebView mNestedWebView = new NestedScrollAgentWebView(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContent, layoutParams)
                .useDefaultIndicator()
                .setWebView(mNestedWebView)
                .setWebChromeClient(webChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(articleLink);
    }

    @Override
    public void onBackPressedSupport() {
        if (!mAgentWeb.back()) {
            super.onBackPressedSupport();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acticle_detail, menu);
        mCollectItem = menu.findItem(R.id.item_collect);
        mCollectItem.setVisible(isShowCollectIcon);
        mCollectItem.setIcon(isCollected ? R.drawable.ic_like_white : R.drawable.ic_like_not_white);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                mPresenter.shareEventWithPermissionVerify(new RxPermissions(this));
                break;
            case R.id.item_collect:
                collectClickEvent();
                break;
            case R.id.item_system_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void collectClickEvent() {
        if (mPresenter.getLoginStatus()) {
            if (isCollected) {
                mPresenter.cancelCollectArticle(articleItemPosition, articleId);
            } else {
                mPresenter.addCollectArticle(articleItemPosition, articleId);
            }
        } else {
            CommonUtils.startLoginActivity(this);
            ToastUtils.showToast(this, getString(R.string.login_first));
        }
    }

    @Override
    public void shareArticle() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_type_url, getString(R.string.app_name), title, articleLink));
        intent.setType("text/plain");
        startActivity(intent);
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu      Menu
     * @return menu if opened
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Constants.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    @SuppressLint("PrivateApi")
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        title = bundle.getString(Constants.ARTICLE_TITLE);
        articleLink = bundle.getString(Constants.ARTICLE_LINK);
        articleId = bundle.getInt(Constants.ARTICLE_ID);
        isCollected = bundle.getBoolean(Constants.IS_COLLECTED);
        isShowCollectIcon = bundle.getBoolean(Constants.IS_SHOW_COLLECT_ICON);
        articleItemPosition = bundle.getInt(Constants.ARTICLE_ITEM_POSITION, -1);
        eventBusTag = bundle.getString(Constants.EVENT_BUS_TAG);
    }

    @Override
    public void shareError() {
        ToastUtils.showToast(this, getString(R.string.write_permission_not_allowed));

    }

    @Override
    public void showCollectSuccess(int position) {
        isCollected = true;
        mCollectItem.setIcon(R.drawable.ic_like_white);
        if (position < 0) {
            ToastUtils.showToast(this, getString(R.string.collect_success));
        } else {
            EventBus.getDefault().post(new CollectEvent(false, position), eventBusTag);
        }
    }

    @Override
    public void showCancelCollectSuccess(int position) {
        isCollected = false;
        mCollectItem.setIcon(R.drawable.ic_like_not_white);
        if (position < 0) {
            ToastUtils.showToast(this, getString(R.string.cancel_collect));
        } else {
            EventBus.getDefault().post(new CollectEvent(true, position), eventBusTag);
        }
    }
}
