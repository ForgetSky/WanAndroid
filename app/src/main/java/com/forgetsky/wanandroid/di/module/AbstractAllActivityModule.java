package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.di.component.BaseActivityComponent;
import com.forgetsky.wanandroid.modules.login.ui.LoginActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.ArticleDetailActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.CommonActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.MainActivity;
import com.forgetsky.wanandroid.modules.main.ui.activity.SearchActivity;

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


}
