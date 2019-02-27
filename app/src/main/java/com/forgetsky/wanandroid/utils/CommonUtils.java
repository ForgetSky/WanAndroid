package com.forgetsky.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;

import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.constant.Constants;
import com.forgetsky.wanandroid.modules.main.ui.ArticleDetailActivity;

import java.util.Random;

public class CommonUtils {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static void startArticleDetailActivity(Context mActivity, int id, String articleTitle,
                                                  String articleLink) {
        Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, id);
        intent.putExtra(Constants.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constants.ARTICLE_LINK, articleLink);

        mActivity.startActivity(intent);

    }

    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red = random.nextInt(190);
        int green = random.nextInt(190);
        int blue = random.nextInt(190);
//        if (SettingUtil.getIsNightMode()) {
            //150-255
//            red = random.nextInt(105) + 150
//            green = random.nextInt(105) + 150
//            blue = random.nextInt(105) + 150
//        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }
}
