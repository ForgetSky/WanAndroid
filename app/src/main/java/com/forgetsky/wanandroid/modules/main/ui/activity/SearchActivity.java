package com.forgetsky.wanandroid.modules.main.ui.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
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

/**
 * @author: ForgetSky
 * @date: 2019/2/28
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.hot_search_flow_layout)
    TagFlowLayout mTopSearchFlowLayout;

    private List<TopSearchData> mTopSearchDataList;

    @Override
    protected void initView() {

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

    private void goToSearchResult(String key) {
//        mPresenter?.saveSearchKey(key)
        Intent intent = new Intent(SearchActivity.this, CommonActivity.class);
        intent.putExtra(Constants.TYPE_FRAGMENT_KEY, Constants.TYPE_SEARCH_RESULT);
        intent.putExtra(Constants.SEARCH_KEY, key);
        startActivity(intent);
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
            mGoButton.setImageResource(R.drawable.ic_search_white_24dp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return super.onCreateOptionsMenu(menu);
    }

}
