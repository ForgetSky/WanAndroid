package com.forgetsky.wanandroid.core.http.api;

import com.forgetsky.wanandroid.core.http.BaseResponse;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int pageNum);

}
