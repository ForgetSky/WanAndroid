package com.forgetsky.wanandroid.modules.homepager.ui;

import android.app.ActivityOptions;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.contract.HomePagerContract;
import com.forgetsky.wanandroid.modules.homepager.presenter.HomePagerPresenter;
import com.forgetsky.wanandroid.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter> implements HomePagerContract.View {

    private static final String TAG = "HomePagerFragment";

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.home_pager_recycler_view)
    RecyclerView mRecyclerView;

    private List<ArticleItemData> mArticleItemDataList;
    private ArticleListAdapter mAdapter;
    private int articlePosition;

    public static HomePagerFragment getInstance() {
        HomePagerFragment fragment = new HomePagerFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected void initEventAndData() {
        for (int i = 0; i < 30; i++) {
            ArticleItemData  data= new ArticleItemData();
            data.setTestString("我是第" + i + "条标题");
            mArticleItemDataList.add(data);
        }
        initRefreshLayout();
    }

    private void initRecyclerView() {
        mArticleItemDataList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_article_list, mArticleItemDataList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        //add head banner
//        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.head_banner, null));
//        mBanner = mHeaderGroup.findViewById(R.id.head_banner);
//        mHeaderGroup.removeView(mBanner);
//        mAdapter.addHeaderView(mBanner);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshLayout(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    private void startArticleDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        ToastUtils.showToast(getActivity(),"you click the position :" + String.valueOf(position),Toast.LENGTH_SHORT);
        //记录点击的文章位置，便于在文章内点击收藏返回到此界面时能展示正确的收藏状态
//        articlePosition = position;
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
//        JudgeUtils.startArticleDetailActivity(_mActivity,
//                options,
//                mAdapter.getData().get(position).getId(),
//
//                false,
//                false);
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
    public void showLogoutSuccess() {

    }

    public void jumpToTheTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
