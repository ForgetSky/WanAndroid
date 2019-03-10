package com.forgetsky.wanandroid.core.http.api;

import com.forgetsky.wanandroid.core.http.BaseResponse;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleItemData>>> getTopArticles();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    /**
     * 热搜
     * http://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k POST search key
     * @return 搜索数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> getSearchResultList(@Path("page") int page, @Field("k") String k);

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登录数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     *
     * @return 登录数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id article id
     * @return 收藏站内文章数据
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<ArticleListData>> addCollectArticle(@Path("id") int id);

    /**
     * 收藏站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     *
     * @param title title
     * @param author author
     * @param link link
     * @return 收藏站外文章数据
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> addCollectOutsideArticle(@Field("title") String  title, @Field("author") String author, @Field("link") String link);


    /**
     * 获取收藏列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page page number
     * @return 收藏列表数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getCollectList(@Path("page") int page);

    /**
     * 文章列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * @param id 列表中文章的id
     * @return 取消站内文章数据
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<ArticleListData>> cancelCollectArticle(@Path("id") int id);

    /**
     * 收藏列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect/2805/json
     *
     * @param id article id
     * @param originId originId 代表的是你收藏之前的那篇文章本身的id；
     *                 但是收藏支持主动添加，这种情况下，没有originId则为-1
     * @return 取消收藏列表中文章数据
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(@Path("id") int id, @Field("originId") int originId);

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

}
