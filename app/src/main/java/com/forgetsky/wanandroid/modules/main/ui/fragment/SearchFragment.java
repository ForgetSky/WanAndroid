package com.forgetsky.wanandroid.modules.main.ui.fragment;

import android.view.Menu;
import android.view.MenuInflater;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.base.fragment.BaseFragment;
import com.forgetsky.wanandroid.modules.main.contract.SearchContract;
import com.forgetsky.wanandroid.modules.main.presenter.SearchPresenter;

/**
 * @author: ForgetSky
 * @date: 2019/2/28
 */
public class SearchFragment extends BaseFragment<SearchPresenter> implements SearchContract.View {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
