package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.di.component.BaseActivityComponent;
import com.forgetsky.wanandroid.modules.main.ui.ArticleDetailActivity;
import com.forgetsky.wanandroid.modules.main.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();
}
