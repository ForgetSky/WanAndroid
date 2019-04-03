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

package com.forgetsky.wanandroid.core.constant;


import android.graphics.Color;


public class Constants {

    public static final String MY_SHARED_PREFERENCE = "my_shared_preference";

    /**
     * url
     */
    public static final String COOKIE = "Cookie";

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
    public static final int TYPE_ABOUT_US = 9;

    public static final String TYPE_FRAGMENT_KEY = "type_fragment";
    public static final String CURRENT_FRAGMENT_KEY = "current_fragment";

    public static final String SEARCH_KEY = "search_key";
    public static final String KNOWLEDGE_CID = "knowledge_cid";
    public static final String PROJECT_CID = "project_cid";
    public static final String WX_CHAPTER_ID = "wx_chapter_id";
    public static final String KNOWLEDGE_DATA = "knowledge_data";

    /**
     * TODO
     */
    public static final String TODO_TYPE = "todo_type";
    public static final String TODO_DATA = "todo_data";
    public static final int TODO_TYPE_ALL = 0;
    public static final int TODO_TYPE_WORK = 1;
    public static final int TODO_TYPE_STUDY = 2;
    public static final int TODO_TYPE_LIFE = 3;
    public static final int TODO_TYPE_OTHER = 4;

    public static final int TODO_PRIORITY_FIRST = 1;
    public static final int TODO_PRIORITY_SECOND = 2;

    public static final String KEY_TODO_TITLE = "title";
    public static final String KEY_TODO_CONTENT = "content";
    public static final String KEY_TODO_DATE = "date";
    public static final String KEY_TODO_TYPE = "type";
    public static final String KEY_TODO_STATUS = "status";
    public static final String KEY_TODO_PRIORITY = "priority";
    public static final String KEY_TODO_ORDERBY= "orderby";


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
     * Avoid double click time area
     */

    public static final long DOUBLE_INTERVAL_TIME = 2000;


    public static final String ARTICLE_LINK = "article_link";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_ID = "article_id";

    public static final String IS_COLLECTED = "is_collected";

    public static final String IS_SHOW_COLLECT_ICON = "is_show_collect_icon";

    public static final String ARTICLE_ITEM_POSITION = "article_item_position";

    public static final String DB_NAME = "forgetsky_wan_android.db";

    /**
     * Shared Preference key
     */
    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String AUTO_CACHE_STATE = "auto_cache_state";

    public static final String NO_IMAGE_STATE = "no_image_state";

    public static final String NIGHT_MODE = "night_mode";

    /**
     * EventBus Tag
     */
    public static final String EVENT_BUS_TAG = "event_bus_tag";
    public static final String MAIN_PAGER = "main_pager";
    public static final String COLLECT_PAGER = "collect_pager";
    public static final String SEARCH_PAGER = "search_pager";
    public static final String PROJECT_PAGER = "project_pager";
    public static final String KNOWLEDGE_PAGER = "knowledge_pager";
    public static final String WX_PAGER = "wx_pager";
    public static final String TAG_DEFAULT = "tag_default";

    /**
     * About Url
     */
    public static final String ABOUT_WEBSITE = "https://www.wanandroid.com/about";
    public static final String ABOUT_SOURCE = "https://github.com/ForgetSky/ForgetSkyWanAndroid";
    public static final String ABOUT_FEEDBACK = "https://github.com/ForgetSky/ForgetSkyWanAndroid/issues";

}
