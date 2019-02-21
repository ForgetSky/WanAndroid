package com.forgetsky.wanandroid.di.module;

import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.DataManager;
import com.forgetsky.wanandroid.core.http.HttpHelper;
import com.forgetsky.wanandroid.core.http.HttpHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final WanAndroidApp application;

    public AppModule(WanAndroidApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    WanAndroidApp provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }

}
