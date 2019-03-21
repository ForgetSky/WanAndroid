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

package com.forgetsky.wanandroid.modules.homepager.contract;

import com.forgetsky.wanandroid.modules.homepager.banner.BannerData;
import com.forgetsky.wanandroid.modules.homepager.bean.ArticleListData;
import com.forgetsky.wanandroid.modules.main.contract.CollectEventContract;

import java.util.List;

public interface HomePagerContract {

    interface View extends CollectEventContract.View {
        void showArticleList(ArticleListData articleListData, boolean isRefresh);

        void showBannerData(List<BannerData> bannerDataList);
    }

    interface Presenter extends CollectEventContract.Presenter<View> {

        void getArticleList(boolean isShowStatusView);

        void getBannerData(boolean isShowStatusView);

        void getHomePagerData(boolean isShowStatusView);

        void refreshLayout(boolean isShowStatusView);

        void loadMore();

    }
}
