package com.forgetsky.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.core.greendao.DaoMaster;
import com.forgetsky.wanandroid.core.greendao.DaoSession;
import com.forgetsky.wanandroid.di.component.DaggerAppComponent;
import com.forgetsky.wanandroid.di.module.AppModule;
import com.forgetsky.wanandroid.di.module.HttpModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);

        initGreenDao();

        refWatcher = setupLeakCanary();
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

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        WanAndroidApp application = (WanAndroidApp) context.getApplicationContext();
        return application.mDaoSession;
    }

}
