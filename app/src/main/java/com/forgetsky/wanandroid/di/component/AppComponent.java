package com.forgetsky.wanandroid.di.component;

import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.di.module.AbstractAllActivityModule;
import com.forgetsky.wanandroid.di.module.AbstractAllFragmentModule;
import com.forgetsky.wanandroid.di.module.AppModule;
import com.forgetsky.wanandroid.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    void inject(WanAndroidApp wanAndroidApp);
}
