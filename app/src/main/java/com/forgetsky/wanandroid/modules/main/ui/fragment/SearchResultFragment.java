package com.forgetsky.wanandroid.modules.main.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.homepager.ui.ArticleListAdapter;
import com.forgetsky.wanandroid.modules.main.contract.SearchResultContract;
import com.forgetsky.wanandroid.modules.main.presenter.SearchResultPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ForgetSky
 * @date 19-3-1
 */
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContract.View {

    @BindView(R.id.sr_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.search_result_recycler_view)
    RecyclerView mRecyclerView;
    private List<ArticleItemData> mArticleList;
    private ArticleListAdapter mAdapter;
    private String mSearchKey="";

    public static SearchResultFragment newInstance(Bundle bundle) {
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initEventAndData() {
        assert getArguments() != null;
        mSearchKey = getArguments().getString(Constants.SEARCH_KEY, "");
        initRefreshLayout();
        mPresenter.search(mSearchKey, true);
    }

    private void initRecyclerView() {
        mArticleList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_article_list, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
//        mRefreshLayout.setEnableAutoLoadMore(true);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.search(mSearchKey,false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore();
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startArticleDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        CommonUtils.startArticleDetailActivity(_mActivity,
                mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getTitle(),
                mAdapter.getData().get(position).getLink());
    }

    private void clickChildEvent(View view, int position) {
        switch (view.getId()) {
//            case R.id.item_search_pager_chapterName:
//                startSingleChapterKnowledgePager(position);
//                break;
//            case R.id.item_search_pager_like_iv:
//                likeEvent(position);
//                break;
//            case R.id.item_search_pager_tag_red_tv:
//                clickTag(position);
//                break;
            default:
                break;
        }
    }

    @Override
    public void showSearchResultList(ArticleListData articleListData, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            mArticleList = articleListData.getDatas();
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mArticleList.addAll(articleListData.getDatas());
            mAdapter.addData(articleListData.getDatas());
        }
    }
}
