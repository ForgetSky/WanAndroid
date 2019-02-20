package com.forgetsky.wanandroid.modules.hierarchy.ui;

import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.hierarchy.contract.KnowledgeHierarchyContract;
import com.forgetsky.wanandroid.modules.hierarchy.presenter.KnowledgeHierarchyPresenter;

import butterknife.BindView;

public class KnowledgeHierarchyFragment extends BaseFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.View {

    private static final String TAG = "KnowledgeHierarchyFragment";
    @BindView(R.id.test_view)
    TextView textView;

    public static KnowledgeHierarchyFragment getInstance() {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("KnowledgeHierarchyFragment");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    protected void initEventAndData() {
    }


    @Override
    public void showLogoutSuccess() {

    }
}
