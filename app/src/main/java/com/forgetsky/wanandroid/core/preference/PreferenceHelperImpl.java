package com.forgetsky.wanandroid.core.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.core.constant.Constants;

import javax.inject.Inject;

/**
 * @author ForgetSky
 * @date 19-3-7
 */
public class PreferenceHelperImpl implements PreferenceHelper {
    private final SharedPreferences mPreferences;
    @Inject
    PreferenceHelperImpl() {
        mPreferences = WanAndroidApp.getContext().getSharedPreferences(Constants.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferences.edit().putBoolean(Constants.LOGIN_STATUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(Constants.LOGIN_STATUS, false);
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(Constants.ACCOUNT, account).apply();
    }

    @Override
    public String getLoginAccount() {
        return mPreferences.getString(Constants.ACCOUNT, "");
    }
}
