package com.forgetsky.wanandroid.core.db;

import com.forgetsky.wanandroid.core.greendao.HistoryData;

import java.util.List;

/**
 * @author ForgetSky
 * @date 19-3-4
 */

public interface DbHelper {

    /**
     * Add search history data
     *
     * @param data  added string
     * @return  List<HistoryData>
     */
    List<HistoryData> addHistoryData(String data);

    /**
     * Clear all search history data
     */
    void clearAllHistoryData();

    /**
     * Clear all search history data
     */
    void deleteHistoryDataById(Long id);

    /**
     * Load all history data
     *
     * @return List<HistoryData>
     */
    List<HistoryData> loadAllHistoryData();

}
