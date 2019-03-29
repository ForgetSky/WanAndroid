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

    Observable<BaseResponse<LoginData>> login(String username, String password);

    Observable<BaseResponse<LoginData>> register(String username, String password, String repassword);

    Observable<BaseResponse<LoginData>> logout();

    Observable<BaseResponse<ArticleListData>> addCollectArticle(int id);

    Observable<BaseResponse<ArticleListData>> addCollectOutsideArticle(String title, String author, String link);

    Observable<BaseResponse<ArticleListData>> getCollectList(int page);

    Observable<BaseResponse<ArticleListData>> cancelCollectArticle(int id);

    Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id, int originId);

    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    Observable<BaseResponse<List<ProjectTreeData>>> getProjectTreeData();

    Observable<BaseResponse<ArticleListData>> getProjectListData(int page, int cid);

    Observable<BaseResponse<List<WxChapterData>>> getWxChapterListData();

    Observable<BaseResponse<ArticleListData>> getWxArticlesData(int id, int page);

    Observable<BaseResponse<ArticleListData>> getWxSearchData(int id, int page, String k);

    Observable<BaseResponse<List<KnowledgeTreeData>>> getKnowledgeTreeData();

    Observable<BaseResponse<ArticleListData>> getKnowledgeListData(int page, int cid);

    Observable<BaseResponse<TodoListData>> getTodoListData(int page, Map<String, Object> map);

    Observable<BaseResponse<TodoItemData>> addTodo(Map<String, Object> map);

    Observable<BaseResponse<TodoItemData>> updateTodo(int id, Map<String, Object> map);

    Observable<BaseResponse<TodoItemData>> deleteTodo(int id);

    Observable<BaseResponse<TodoItemData>> updateTodoStatus(int id, int status);

}
