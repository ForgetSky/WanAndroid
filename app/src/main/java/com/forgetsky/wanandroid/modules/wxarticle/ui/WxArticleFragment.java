package com.forgetsky.wanandroid.modules.wxarticle.ui;

import android.widget.TextView;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.wxarticle.contract.WxArticleContract;
import com.forgetsky.wanandroid.modules.wxarticle.presenter.WxArticlePresenter;

import butterknife.BindView;

public class WxArticleFragment extends BaseFragment<WxArticlePresenter> implements WxArticleContract.View {

    private static final String TAG = "WxArticleFragment";
    @BindView(R.id.test_view)
    TextView textView;

    public static WxArticleFragment getInstance() {
        WxArticleFragment fragment = new WxArticleFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("WxArticleFragment");

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
