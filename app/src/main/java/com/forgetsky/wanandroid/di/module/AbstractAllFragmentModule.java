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

import com.forgetsky.wanandroid.di.component.BaseFragmentComponent;
import com.forgetsky.wanandroid.modules.hierarchy.ui.KnowledgeFragment;
import com.forgetsky.wanandroid.modules.hierarchy.ui.KnowledgeListFragment;
import com.forgetsky.wanandroid.modules.homepager.ui.HomePagerFragment;
import com.forgetsky.wanandroid.modules.login.ui.LoginFragment;
import com.forgetsky.wanandroid.modules.login.ui.RegisterFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.AboutFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.CollectFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.SearchResultFragment;
import com.forgetsky.wanandroid.modules.main.ui.fragment.UsefulSitesFragment;
import com.forgetsky.wanandroid.modules.navigation.ui.NavigationFragment;
import com.forgetsky.wanandroid.modules.project.ui.ProjectFragment;
import com.forgetsky.wanandroid.modules.project.ui.ProjectListFragment;
import com.forgetsky.wanandroid.modules.todo.ui.TodoListFragment;
import com.forgetsky.wanandroid.modules.wxarticle.ui.WxArticleFragment;
import com.forgetsky.wanandroid.modules.wxarticle.ui.WxArticleListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = HomePagerFragmentModule.class)
    abstract HomePagerFragment contributesHomePagerFragmentInject();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInject();

    @ContributesAndroidInjector(modules = WxArticleFragmentModule.class)
    abstract WxArticleFragment contributesWxArticleFragmentInject();

    @ContributesAndroidInjector(modules = WxArticleListFragmentModule.class)
    abstract WxArticleListFragment contributesWxArticleListFragmentInject();

    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInject();

    @ContributesAndroidInjector(modules = ProjectListFragmentModule.class)
    abstract ProjectListFragment contributesProjectListFragmentInject();

    @ContributesAndroidInjector(modules = UsefulSitesFragmentModule.class)
    abstract UsefulSitesFragment contributesUsefulSitesFragmentInject();

    @ContributesAndroidInjector(modules = SearchResultFragmentModule.class)
    abstract SearchResultFragment contributesSearchResultFragmentInject();

    @ContributesAndroidInjector(modules = LoginFragmentModule.class)
    abstract LoginFragment contributesLoginFragmentInject();

    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterFragment contributesRegisterFragmentInject();

    @ContributesAndroidInjector(modules = CollectFragmentModule.class)
    abstract CollectFragment contributesCollectFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeFragmentModule.class)
    abstract KnowledgeFragment contributesKnowledgeFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeListFragmentModule.class)
    abstract KnowledgeListFragment contributesKnowledgeListFragmentInject();

    @ContributesAndroidInjector(modules = AboutFragmentModule.class)
    abstract AboutFragment contributesAboutFragmentInject();

    @ContributesAndroidInjector(modules = TodoListFragmentModule.class)
    abstract TodoListFragment contributesTodoListFragmentInject();
}
