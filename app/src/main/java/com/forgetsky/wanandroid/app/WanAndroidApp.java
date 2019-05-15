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

package com.forgetsky.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.forgetsky.wanandroid.BuildConfig;
import com.forgetsky.wanandroid.core.DataManager;
import com.forgetsky.wanandroid.di.component.DaggerAppComponent;
import com.forgetsky.wanandroid.di.module.AppModule;
import com.forgetsky.wanandroid.di.module.HttpModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;

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

    private static Context context;
    private RefWatcher refWatcher;
    @Inject
    public DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);

        if (mDataManager.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }

        refWatcher = setupLeakCanary();

        Bugly.init(getApplicationContext(), "03be19dd60", BuildConfig.DEBUG);
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        WanAndroidApp application = (WanAndroidApp) context.getApplicationContext();
        return application.refWatcher;
    }

    public static Context getContext() {
        return context;
    }

    public static boolean isNightMode() {
        WanAndroidApp application = (WanAndroidApp) context.getApplicationContext();
       return application.mDataManager.isNightMode();
    }

}
