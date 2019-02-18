package com.forgetsky.wanandroid.project.ui;

import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.project.contract.ProjectContract;
import com.forgetsky.wanandroid.project.presenter.ProjectPresenter;

import butterknife.BindView;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    private static final String TAG = "ProjectFragment";
    @BindView(R.id.test_view)
    TextView textView;

    public static ProjectFragment getInstance() {
        ProjectFragment fragment = new ProjectFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("ProjectFragment");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initEventAndData() {
    }


    @Override
    public void showLogoutSuccess() {

    }
}
