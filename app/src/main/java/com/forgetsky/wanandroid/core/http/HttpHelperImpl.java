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

package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.core.http.api.ApiService;
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

    @Override
    public Observable<BaseResponse<List<ProjectTreeData>>> getProjectTreeData() {
        return mApiService.getProjectTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getProjectListData(int page, int cid) {
        return mApiService.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<WxChapterData>>> getWxChapterListData() {
        return mApiService.getWxChapterListData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getWxArticlesData(int id, int page) {
        return mApiService.getWxArticlesData(id, page);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getWxSearchData(int id, int page, String k) {
        return mApiService.getWxSearchData(id, page, k);
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeTreeData>>> getKnowledgeTreeData() {
        return mApiService.getKnowledgeTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getKnowledgeListData(int page, int cid) {
        return mApiService.getKnowledgeListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<TodoListData>> getTodoListData(int page, Map<String, Object> map) {
        return mApiService.getTodoListData(page, map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> addTodo(Map<String, Object> map) {
        return mApiService.addTodo(map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> updateTodo(int id, Map<String, Object> map) {
        return mApiService.updateTodo(id, map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> deleteTodo(int id) {
        return mApiService.deleteTodo(id);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> updateTodoStatus(int id, int status) {
        return mApiService.updateTodoStatus(id, status);
    }
}
