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

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.activity.BaseActivity;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.greendao.HistoryData;
import com.forgetsky.wanandroid.modules.main.adapter.SearchHistoryAdapter;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.contract.SearchContract;
import com.forgetsky.wanandroid.modules.main.presenter.SearchPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: ForgetSky
 * @date: 2019/2/28
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.hot_search_flow_layout)
    TagFlowLayout mTopSearchFlowLayout;
    @BindView(R.id.rv_history_search)
    RecyclerView mRecyclerView;
    private List<TopSearchData> mTopSearchDataList;
    private SearchHistoryAdapter mAdapter;
    private List<HistoryData> mSearchHistoryList;

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
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
        mTopSearchDataList = new ArrayList<>();
        mPresenter.getTopSearchData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadAllHistoryData();
    }

    private void initRecyclerView() {
        mSearchHistoryList = new ArrayList<>();
        mAdapter = new SearchHistoryAdapter(R.layout.item_search_history, mSearchHistoryList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
                return;
            }
            goToSearchResult(mAdapter.getData().get(position).getData());
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clickChildEvent(view, position);
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.search_empty_view);

    }

    @OnClick({R.id.search_history_clear_all_tv})
    void onClick(View view) {
        clearAllHistoryData();
    }

    private void clearAllHistoryData() {
        mPresenter.clearAllHistoryData();
        mSearchHistoryList.clear();
        mAdapter.replaceData(mSearchHistoryList);
    }

    @Override
    public void showTopSearchData(List<TopSearchData> topSearchData) {
        mTopSearchDataList = topSearchData;
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchData>(topSearchData) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchData topSearchData) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.flow_layout_tv, parent, false);
                if (topSearchData != null) {
                    tv.setText(topSearchData.getName());
                    tv.setTextColor(CommonUtils.getRandomColor());
                }
                return tv;
            }
        });

        mTopSearchFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
            goToSearchResult(mTopSearchDataList.get(position1).getName().trim());
            return true;
        });
    }

    @Override
    public void showHistoryData(List<HistoryData> historyDataList) {
        mSearchHistoryList = historyDataList;
        mAdapter.replaceData(historyDataList);

    }

    private void goToSearchResult(String searchString) {
        mPresenter.addHistoryData(searchString);
        Intent intent = new Intent(SearchActivity.this, CommonActivity.class);
        intent.putExtra(Constants.TYPE_FRAGMENT_KEY, Constants.TYPE_SEARCH_RESULT);
        intent.putExtra(Constants.SEARCH_KEY, searchString);
        startActivity(intent);
    }

    private void clickChildEvent(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_clear:
                mPresenter.deleteHistoryDataById(mAdapter.getData().get(position).getId());
                mAdapter.remove(position);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search_button);

        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search_tint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                goToSearchResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setSubmitButtonEnabled(true);

        Field field;
        try {
            field = mSearchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView mGoButton = (ImageView) field.get(mSearchView);
            mGoButton.setImageResource(R.drawable.ic_search);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return super.onCreateOptionsMenu(menu);
    }

}
