package com.forgetsky.wanandroid.modules.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author ForgetSky
 * @date 19-2-25
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}
