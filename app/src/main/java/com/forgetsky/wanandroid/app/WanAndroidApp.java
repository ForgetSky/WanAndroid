package com.forgetsky.wanandroid.app;

import android.app.Activity;
import android.app.Application;

import com.forgetsky.wanandroid.di.component.DaggerAppComponent;
import com.forgetsky.wanandroid.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WanAndroidApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build().inject(this);
    }
}
