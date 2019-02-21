package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.core.http.api.ApiService;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * 对外隐藏进行网络请求的实现细节
 *
 * @author quchao
 * @date 2017/11/27
 */

public class HttpHelperImpl implements HttpHelper {

    private ApiService mApiService;

    @Inject
    HttpHelperImpl(ApiService apiService) {
        mApiService = apiService;
    }


    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int pageNum) {
        return mApiService.getArticleList(pageNum);
    }
}
