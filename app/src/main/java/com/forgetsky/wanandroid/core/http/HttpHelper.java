package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;

import java.util.List;

import io.reactivex.Observable;


public interface HttpHelper {

    /**
     * 获取文章列表
     *
     * @param pageNum 页数
     * @return 文章列表数据
     */
    Observable<BaseResponse<ArticleListData>> getArticleList(int pageNum);

    Observable<BaseResponse<List<BannerData>>> getBannerData();

    Observable<BaseResponse<List<ArticleItemData>>> getTopArticles();

    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    Observable<BaseResponse<ArticleListData>> getSearchResultList(int pageNum, String k);
}
