package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.core.http.api.ApiService;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;

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

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mApiService.login(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> register(String username, String password, String repassword) {
        return mApiService.register(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApiService.logout();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> addCollectArticle(int id) {
        return mApiService.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mApiService.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getCollectList(int page) {
        return mApiService.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectArticle(int id) {
        return mApiService.cancelCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id, int originId) {
        return mApiService.cancelCollectInCollectPage(id, originId);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mApiService.getNavigationListData();
    }
}
