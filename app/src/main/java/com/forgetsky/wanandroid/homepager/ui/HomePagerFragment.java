package com.forgetsky.wanandroid.homepager.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.homepager.contract.HomePagerContract;
import com.forgetsky.wanandroid.homepager.presenter.HomePagerPresenter;

import butterknife.BindView;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter> implements HomePagerContract.View {

    private static final String TAG = "HomePagerFragment";
    @BindView(R.id.test_view)
    TextView textView;

    public static HomePagerFragment getInstance() {
        HomePagerFragment fragment = new HomePagerFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("HomePagerFragment");

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
