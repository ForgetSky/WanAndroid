package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.core.http.api.ApiService;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


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

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApiService.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemData>>> getTopArticles() {
        return mApiService.getTopArticles();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mApiService.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mApiService.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getSearchResultList(int pageNum, String k) {
        return mApiService.getSearchResultList(pageNum, k);
    }
}
