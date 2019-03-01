package com.forgetsky.wanandroid.modules.main.ui.fragment;

import android.os.Bundle;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.main.contract.SearchResultContract;
import com.forgetsky.wanandroid.modules.main.presenter.SearchResultPresenter;

/**
 * @author ForgetSky
 * @date 19-3-1
 */
public class SearchResultFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContract.View {
    public static SearchResultFragment newInstance(Bundle bundle) {
        return new SearchResultFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initEventAndData() {

    }
}
