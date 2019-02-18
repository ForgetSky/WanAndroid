package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.di.component.BaseFragmentComponent;
import com.forgetsky.wanandroid.hierarchy.ui.KnowledgeHierarchyFragment;
import com.forgetsky.wanandroid.homepager.ui.HomePagerFragment;
import com.forgetsky.wanandroid.navigation.ui.NavigationFragment;
import com.forgetsky.wanandroid.project.ui.ProjectFragment;
import com.forgetsky.wanandroid.wxarticle.ui.WxArticleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = HomePagerFragmentModule.class)
    abstract HomePagerFragment contributesHomePagerFragmentInject();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInject();

    @ContributesAndroidInjector(modules = KnowledgeHierarchyFragmentModule.class)
    abstract KnowledgeHierarchyFragment contributesKnowledgeHierarchyFragmentInject();

    @ContributesAndroidInjector(modules = WxArticleFragmentModule.class)
    abstract WxArticleFragment contributesWxArticleFragmentInject();

    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInject();
}
