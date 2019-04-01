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

package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.di.component.BaseActivityComponent;
import com.forgetsky.wanandroid.modules.hierarchy.ui.KnowledgeActivity;
import com.forgetsky.wanandroid.modules.login.ui.LoginActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.ArticleDetailActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.CommonActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.MainActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.SearchActivity;
import com.forgetsky.wanandroid.modules.todo.ui.AddTodoActivity;
import com.forgetsky.wanandroid.modules.todo.ui.TodoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();

    @ContributesAndroidInjector(modules = CommonActivityModule.class)
    abstract CommonActivity contributesCommonActivityInjector();

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity contributesSearchActivityInject();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInject();

    @ContributesAndroidInjector(modules = KnowledgeActivityModule.class)
    abstract KnowledgeActivity contributesKnowledgeActivityInject();

    @ContributesAndroidInjector(modules = TodoActivityModule.class)
    abstract TodoActivity contributesTodoActivityInject();

    @ContributesAndroidInjector(modules = AddTodoActivityModule.class)
    abstract AddTodoActivity contributesAddTodoActivityInject();

}
