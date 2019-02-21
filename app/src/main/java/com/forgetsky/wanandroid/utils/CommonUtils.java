package com.forgetsky.wanandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.forgetsky.wanandroid.app.WanAndroidApp;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class CommonUtils {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }


}
