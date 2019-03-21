package com.forgetsky.wanandroid.modules.main.ui.fragment;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.utils.CacheUtils;

/**
 * Created by ForgetSky on 2019/3/18.
 */
public class SettingFragment extends PreferenceFragmentCompat {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_setting);
        findPreference("clearCache").setSummary(CacheUtils.getTotalCacheSize());
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        switch (preference.getKey()) {
            case "clearCache":
                CacheUtils.clearAllCache();
                findPreference(preference.getKey()).setSummary(CacheUtils.getTotalCacheSize());
                break;
            default:
                break;
        }
        return super.onPreferenceTreeClick(preference);
    }
}
