/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.forgetsky.wanandroid.core.http.api;

import com.forgetsky.wanandroid.core.http.BaseResponse;
import com.forgetsky.wanandroid.modules.hierarchy.bean.KnowledgeTreeData;
import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleItemData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.login.bean.LoginData;
import com.forgetsky.wanandroid.modules.main.bean.TopSearchData;
import com.forgetsky.wanandroid.modules.main.bean.UsefulSiteData;
import com.forgetsky.wanandroid.modules.navigation.bean.NavigationListData;
import com.forgetsky.wanandroid.modules.project.bean.ProjectTreeData;
import com.forgetsky.wanandroid.modules.todo.bean.TodoItemData;
import com.forgetsky.wanandroid.modules.todo.bean.TodoListData;
import com.forgetsky.wanandroid.modules.wxarticle.bean.WxChapterData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int pageNum);

    /**
     * 广告栏
     * https://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 获取首页置顶文章列表
     * https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleItemData>>> getTopArticles();

    /**
     * 常用网站
     * https://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();

    /**
     * 热搜
     * https://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<TopSearchData>>> getTopSearchData();

    /**
     * 搜索
     * https://www.wanandroid.com/article/query/0/json
     *
     * @param page page
     * @param k    POST search key
     * @return 搜索数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> getSearchResultList(@Path("page") int page, @Field("k") String k);

    /**
     * 登录
     * https://www.wanandroid.com/user/login
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
     * https://www.wanandroid.com/user/register
     *
     * @param username   user name
     * @param password   password
     * @param repassword re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 退出登录
     * https://www.wanandroid.com/user/logout/json
     *
     * @return 登录数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 收藏站内文章
     * https://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id article id
     * @return 收藏站内文章数据
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<ArticleListData>> addCollectArticle(@Path("id") int id);

    /**
     * 收藏站外文章
     * https://www.wanandroid.com/lg/collect/add/json
     *
     * @param title  title
     * @param author author
     * @param link   link
     * @return 收藏站外文章数据
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> addCollectOutsideArticle(@Field("title") String title, @Field("author") String author, @Field("link") String link);


    /**
     * 获取收藏列表
     * https://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page page number
     * @return 收藏列表数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getCollectList(@Path("page") int page);

    /**
     * 文章列表中取消收藏文章
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * @param id 列表中文章的id
     * @return 取消站内文章数据
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<ArticleListData>> cancelCollectArticle(@Path("id") int id);

    /**
     * 收藏列表中取消收藏文章
     * https://www.wanandroid.com/lg/uncollect/2805/json
     *
     * @param id       article id
     * @param originId originId 代表的是你收藏之前的那篇文章本身的id；
     *                 但是收藏支持主动添加，这种情况下，没有originId则为-1
     * @return 取消收藏列表中文章数据
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(@Path("id") int id, @Field("originId") int originId);

    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectTreeData>>> getProjectTreeData();

    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid  child page id
     * @return 项目列表数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getProjectListData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WxChapterData>>> getWxChapterListData();

    /**
     * 获取当前公众号的数据
     * https://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListData>> getWxArticlesData(@Path("id") int id, @Path("page") int page);

    /**
     * 指定搜索内容，搜索当前公众号的某页的此类数据
     * https://wanandroid.com/wxarticle/list/405/1/json?k=Java
     *
     * @param id
     * @param page
     * @param k
     * @return 指定搜索内容，搜索当前公众号的某页的此类数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListData>> getWxSearchData(@Path("id") int id, @Path("page") int page, @Query("k") String k);

    /**
     * 知识体系
     * https://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeTreeData>>> getKnowledgeTreeData();

    /**
     * 知识体系下的文章
     * https://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid  second page id
     * @return 知识体系文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getKnowledgeListData(@Path("page") int page, @Query("cid") int cid);


    /**
     * 获取TODO列表
     * https://www.wanandroid.com/lg/todo/v2/list/{page}/json
     * <p>
     * 页码从1开始，拼接在url 上
     * status 状态， 1-完成；0未完成; 默认全部展示；
     * type 创建时传入的类型, 默认全部展示
     * priority 创建时传入的优先级；默认全部展示
     * orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；（1和2只能获取到已完成的TODO）
     *
     * @return
     */
    @Headers("Cache-Control: max-age=0")
    @GET("lg/todo/v2/list/{page}/json")
    Observable<BaseResponse<TodoListData>> getTodoListData(@Path("page") int page, @QueryMap Map<String, Object> map);

    /**
     * 新增一条TODO
     * https://www.wanandroid.com/lg/todo/add/json
     * <p>
     * title: 新增标题（必须）
     * content: 新增详情（可选）
     * date: 2018-08-01 预定完成时间（不传默认当天，建议传）
     * type: 大于0的整数（可选）；
     * priority 大于0的整数（可选）；
     *
     * @return
     */
    @Headers("Cache-Control: max-age=0")
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemData>> addTodo(@FieldMap Map<String, Object> map);

    /**
     * 更新一条TODO
     * https://www.wanandroid.com/lg/todo/update/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识
     * title: 更新标题 （必须）
     * content: 新增详情（必须）
     * date: 2018-08-01（必须）
     * status: 0 // 0为未完成，1为完成
     * type: ；
     * priority: ；
     *
     * @return
     */
    @Headers("Cache-Control: max-age=0")
    @POST("lg/todo/update/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemData>> updateTodo(@Path("id") int id, @FieldMap Map<String, Object> map);

    /**
     * 删除一条TODO
     * https://www.wanandroid.com/lg/todo/delete/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识

     * @return
     */
    @Headers("Cache-Control: max-age=0")
    @POST("lg/todo/delete/{id}/json")
    Observable<BaseResponse<TodoItemData>> deleteTodo(@Path("id") int id);

    /**
     * 仅更新完成状态TODO
     * https://www.wanandroid.com/lg/todo/done/{id}/json
     * <p>
     * id: 拼接在链接上，为唯一标识
     * status: 0或1，传1代表未完成到已完成，反之则反之。
     * @return
     */
    @Headers("Cache-Control: max-age=0")
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<TodoItemData>> updateTodoStatus(@Path("id") int id, @Field("status") int status);
}
