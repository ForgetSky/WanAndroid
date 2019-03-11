package com.forgetsky.wanandroid.core.constant;


import android.graphics.Color;

import com.forgetsky.wanandroid.app.WanAndroidApp;

import java.io.File;


public class Constants {

    public static final String MY_SHARED_PREFERENCE = "my_shared_preference";

    /**
     * url
     */
    public static final String COOKIE = "Cookie";

    /**
     * Path
     */
    public static final String PATH_DATA = WanAndroidApp.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * Tag fragment classify
     */
    public static final int TYPE_HOME_PAGER = 0;
    public static final int TYPE_KNOWLEDGE = 1;
    public static final int TYPE_NAVIGATION = 2;
    public static final int TYPE_WX_ARTICLE = 3;
    public static final int TYPE_PROJECT = 4;

    public static final int TYPE_COLLECT = 5;

    public static final int TYPE_SETTING = 6;
    public static final int TYPE_USEFULSITES = 7;
    public static final int TYPE_SEARCH_RESULT = 8;
    public static final int TYPE_LOGIN = 9;
    public static final int TYPE_REGISTER = 10;

    public static final String TYPE_FRAGMENT_KEY = "type_fragment";
    public static final String CURRENT_FRAGMENT_KEY = "current_fragment";

    public static final String SEARCH_KEY = "search_key";
    /**
     * Bottom Navigation tab classify
     */
    public static final int TAB_ONE = 0;

    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";

    /**
     * Phone MANUFACTURER
     */
    public static final String SAMSUNG = "samsung";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };


    public static final String MENU_BUILDER = "MenuBuilder";

    /**
     * Refresh theme color
     */
//    public static final int BLUE_THEME = R.color.colorPrimary;

    /**
     * Avoid double click time area
     */
    public static final long CLICK_TIME_AREA = 1000;

    public static final long DOUBLE_INTERVAL_TIME = 2000;


    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECTED = "is_collected";

    public static final String IS_SHOW_COLLECT_ICON = "is_show_collect_icon";

    public static final String IS_COLLECT_PAGE = "is_collect_page";

    public static final String ARTICLE_ITEM_POSITION = "article_item_position";

    public static final String CHAPTER_ID = "chapter_id";

    public static final String IS_SINGLE_CHAPTER = "is_single_chapter";

    public static final String CHAPTER_NAME = "is_chapter_name";

    public static final String SUPER_CHAPTER_NAME = "super_chapter_name";

    public static final String DB_NAME = "forgetsky_wan_android.db";

    public static final String CURRENT_PAGE = "current_page";

    public static final String PROJECT_CURRENT_PAGE = "project_current_page";

    /**
     * Shared Preference key
     */
    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String AUTO_CACHE_STATE = "auto_cache_state";

    public static final String NO_IMAGE_STATE = "no_image_state";

    public static final String NIGHT_MODE_STATE = "night_mode_state";

    /**
     * EventBus Tag
     */
    public static final String EVENT_BUS_TAG = "event_bus_tag";
    public static final String MAIN_PAGER = "main_pager";
    public static final String COLLECT_PAGER = "collect_pager";
    public static final String SEARCH_PAGER = "search_pager";
    public static final String TAG_DEFAULT = "tag_default";
}
