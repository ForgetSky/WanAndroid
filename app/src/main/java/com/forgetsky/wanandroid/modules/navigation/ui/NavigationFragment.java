package com.forgetsky.wanandroid.modules.navigation.ui;

import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.navigation.contract.NavigationContract;
import com.forgetsky.wanandroid.modules.navigation.presenter.NavigationPresenter;

import butterknife.BindView;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    private static final String TAG = "NavigationFragment";
    @BindView(R.id.test_view)
    TextView textView;

    public static NavigationFragment getInstance() {
        NavigationFragment fragment = new NavigationFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("NavigationFragment");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData() {
    }


    @Override
    public void showLogoutSuccess() {

    }
}
