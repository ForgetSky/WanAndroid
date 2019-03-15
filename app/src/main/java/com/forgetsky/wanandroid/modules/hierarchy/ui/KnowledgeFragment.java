package com.forgetsky.wanandroid.modules.hierarchy.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.hierarchy.bean.KnowledgeTreeData;
import com.forgetsky.wanandroid.modules.hierarchy.contract.KnowledgeContract;
import com.forgetsky.wanandroid.modules.hierarchy.presenter.KnowledgePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    private static final String TAG = "KnowledgeFragment";

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.knowledge_list_recycler_view)
    RecyclerView mRecyclerView;

    private List<KnowledgeTreeData> mKnowledgeTreeData;
    private KnowledgeTreeAdapter mAdapter;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }


    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    protected void initEventAndData() {
        initRefreshLayout();
        mPresenter.getKnowledgeTreeData();
    }

    private void initRecyclerView() {
        mKnowledgeTreeData = new ArrayList<>();
        mAdapter = new KnowledgeTreeAdapter(R.layout.item_knowledge_tree_list, mKnowledgeTreeData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startKnowledgeActivity(position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startKnowledgeActivity(int position) {
        Intent intent = new Intent(_mActivity, KnowledgeActivity.class);
        intent.putExtra(Constants.KNOWLEDGE_DATA, mAdapter.getData().get(position));
        startActivity(intent);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.getKnowledgeTreeData();
            refreshLayout.finishRefresh();
        });
    }

    public void jumpToTheTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showKnowledgeTreeData(List<KnowledgeTreeData> knowledgeTreeData) {
        if (mAdapter.getData().size() < knowledgeTreeData.size()) {
            mKnowledgeTreeData = knowledgeTreeData;
            mAdapter.replaceData(mKnowledgeTreeData);
        }
    }
}
