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

package com.forgetsky.wanandroid.core;

import com.forgetsky.wanandroid.core.db.DbHelper;
import com.forgetsky.wanandroid.core.greendao.HistoryData;
import com.forgetsky.wanandroid.core.http.BaseResponse;
import com.forgetsky.wanandroid.core.http.HttpHelper;
import com.forgetsky.wanandroid.core.preference.PreferenceHelper;
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

public class DataManager implements HttpHelper, DbHelper,PreferenceHelper {
    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper httpHelper, DbHelper dbHelper, PreferenceHelper preferenceHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferenceHelper = preferenceHelper;
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int pageNum) {
        return mHttpHelper.getArticleList(pageNum);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemData>>> getTopArticles() {
        return mHttpHelper.getTopArticles();
    }

    @Override
    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mHttpHelper.getUsefulSites();
    }

    @Override
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mHttpHelper.getTopSearchData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getSearchResultList(int pageNum, String k) {
        return mHttpHelper.getSearchResultList(pageNum, k);
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mHttpHelper.login(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> register(String username, String password, String repassword) {
        return mHttpHelper.register(username, password, repassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> addCollectArticle(int id) {
        return mHttpHelper.addCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mHttpHelper.addCollectOutsideArticle(title, author, link);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getCollectList(int page) {
        return mHttpHelper.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectArticle(int id) {
        return mHttpHelper.cancelCollectArticle(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id, int originId) {
        return mHttpHelper.cancelCollectInCollectPage(id, originId);
    }

    @Override
    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mHttpHelper.getNavigationListData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectTreeData>>> getProjectTreeData() {
        return mHttpHelper.getProjectTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getProjectListData(int page, int cid) {
        return mHttpHelper.getProjectListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<List<WxChapterData>>> getWxChapterListData() {
        return mHttpHelper.getWxChapterListData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getWxArticlesData(int id, int page) {
        return mHttpHelper.getWxArticlesData(id, page);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getWxSearchData(int id, int page, String k) {
        return mHttpHelper.getWxSearchData(id, page, k);
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeTreeData>>> getKnowledgeTreeData() {
        return mHttpHelper.getKnowledgeTreeData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getKnowledgeListData(int page, int cid) {
        return mHttpHelper.getKnowledgeListData(page, cid);
    }

    @Override
    public Observable<BaseResponse<TodoListData>> getTodoListData(int page, Map<String, Object> map) {
        return mHttpHelper.getTodoListData(page, map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> addTodo(Map<String, Object> map) {
        return mHttpHelper.addTodo(map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> updateTodo(int id, Map<String, Object> map) {
        return mHttpHelper.updateTodo(id, map);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> deleteTodo(int id) {
        return mHttpHelper.deleteTodo(id);
    }

    @Override
    public Observable<BaseResponse<TodoItemData>> updateTodoStatus(int id, int status) {
        return mHttpHelper.updateTodoStatus(id, status);
    }

    @Override
    public List<HistoryData> addHistoryData(String data) {
        return mDbHelper.addHistoryData(data);
    }

    @Override
    public void clearAllHistoryData() {
        mDbHelper.clearAllHistoryData();
    }

    @Override
    public void deleteHistoryDataById(Long id) {
        mDbHelper.deleteHistoryDataById(id);
    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return mDbHelper.loadAllHistoryData();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public void setNightMode(boolean isNightMode) {
        mPreferenceHelper.setNightMode(isNightMode);
    }

    @Override
    public boolean isNightMode() {
        return mPreferenceHelper.isNightMode();
    }
}
