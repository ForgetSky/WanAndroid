package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.di.component.BaseFragmentComponent;
import com.forgetsky.wanandroid.homepager.ui.HomePagerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = HomePagerFragmentModule.class)
    abstract HomePagerFragment contributesHomePagerFragmentInject();


}
