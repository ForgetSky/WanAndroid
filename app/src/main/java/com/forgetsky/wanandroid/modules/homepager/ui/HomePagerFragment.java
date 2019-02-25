package com.forgetsky.wanandroid.modules.homepager.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerGlideImageLoader;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.homepager.contract.HomePagerContract;
import com.forgetsky.wanandroid.modules.homepager.presenter.HomePagerPresenter;
import com.forgetsky.wanandroid.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter> implements HomePagerContract.View {

    private static final String TAG = "HomePagerFragment";

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.home_pager_recycler_view)
    RecyclerView mRecyclerView;

    private List<ArticleItemData> mArticleList;
    private ArticleListAdapter mAdapter;
    private int articlePosition;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private List<Integer> bannerIdList;
    private Banner mBanner;

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
        initRefreshLayout();
        mPresenter.refreshLayout(true);
    }

    private void initRecyclerView() {
        mArticleList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_article_list, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChildEvent(view, position));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        //add head banner
        LinearLayout mHeaderGroup = (LinearLayout) getLayoutInflater().inflate(R.layout.head_banner, null);
        mBanner = mHeaderGroup.findViewById(R.id.head_banner);

        mHeaderGroup.removeView(mBanner);
        mAdapter.setHeaderView(mBanner);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
//        mRefreshLayout.setEnableAutoLoadMore(true);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshLayout(false);
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
    public void showLogoutSuccess() {

    }

    @Override
    public void showArticleList(ArticleListData articleListData, boolean isRefresh) {
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

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        bannerIdList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
            bannerIdList.add(bannerData.getId());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(i ->
                CommonUtils.startArticleDetailActivity(_mActivity, bannerIdList.get(i), mBannerTitleList.get(i), mBannerUrlList.get(i) )
        );
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    public void jumpToTheTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
