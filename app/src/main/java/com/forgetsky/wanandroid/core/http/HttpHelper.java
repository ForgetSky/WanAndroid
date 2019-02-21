package com.forgetsky.wanandroid.core.http;

import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;

import io.reactivex.Observable;


public interface HttpHelper {

    /**
     * 获取文章列表
     *
     * @param pageNum 页数
     * @return 文章列表数据
     */
    Observable<BaseResponse<ArticleListData>> getArticleList(int pageNum);


}
