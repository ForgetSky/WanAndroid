package com.forgetsky.wanandroid.modules.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.core.greendao.HistoryData;

import java.util.List;

public class SearchHistoryAdapter extends BaseQuickAdapter<HistoryData, BaseViewHolder> {


    public SearchHistoryAdapter(int layoutResId, @Nullable List<HistoryData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryData item) {
        helper.setText(R.id.tv_search_key, item.getData())
        .addOnClickListener(R.id.iv_clear);

    }
}
