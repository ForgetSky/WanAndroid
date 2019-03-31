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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.adapter.CollectListAdapter;
import com.forgetsky.wanandroid.modules.main.contract.CollectContract;
import com.forgetsky.wanandroid.modules.main.presenter.CollectPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.forgetsky.wanandroid.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ForgetSky on 19-3-8.
 */
public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.View {
    @BindView(R.id.collect_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.collect_recycler_view)
    RecyclerView mRecyclerView;
    private List<ArticleItemData> mCollectList;
    private CollectListAdapter mAdapter;

    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initEventAndData() {
        initRefreshLayout();
        mPresenter.getCollectArticle(true);
    }

    private void initRecyclerView() {
        mCollectList = new ArrayList<>();
        mAdapter = new CollectListAdapter(R.layout.item_article_list, mCollectList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getCollectArticle(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    private void startArticleDetailPager(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        CommonUtils.startArticleDetailActivity(_mActivity,
                //need originId to cancel collect, different from other pages
                mAdapter.getData().get(position).getOriginId(),
                mAdapter.getData().get(position).getTitle(),
                mAdapter.getData().get(position).getLink(),
                true,
                true, position, Constants.COLLECT_PAGER);
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_article_like:
                cancelCollect(position);
                break;
//            case R.id.tv_article_chapterName:
//                break;
//            case R.id.tv_article_tag:
//                break;
            default:
                break;
        }
    }

    private void cancelCollect(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() <= position) {
            return;
        }
        mPresenter.cancelCollectInCollectPage(position, mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getOriginId());
    }

    @Override
    public void showCollectList(ArticleListData articleListData, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mAdapter.addData(articleListData.getDatas());
        }
    }

    @Override
    public void showCollectSuccess(int position) {
        ToastUtils.showToast(_mActivity, getString(R.string.collect_success));
    }

    @Override
    public void showCancelCollectSuccess(int position) {
        mAdapter.remove(position);
        ToastUtils.showToast(_mActivity, getString(R.string.cancel_collect));
    }
}
