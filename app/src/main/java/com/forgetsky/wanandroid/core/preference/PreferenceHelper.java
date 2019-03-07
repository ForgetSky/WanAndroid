package com.forgetsky.wanandroid.core.preference;

/**
 * @author ForgetSky
 * @date 19-3-7
 */

public interface PreferenceHelper {
    void setLoginStatus(boolean isLogin);
    boolean getLoginStatus();

    void setLoginAccount(String account);
    String getLoginAccount();
}
