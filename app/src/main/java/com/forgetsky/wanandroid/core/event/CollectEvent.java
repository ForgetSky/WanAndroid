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

package com.forgetsky.wanandroid.core.event;

public class CollectEvent {
    private boolean isCancel;
    private int articlePostion;

    public CollectEvent(boolean isCancel, int articlePostion) {
        this.isCancel = isCancel;
        this.articlePostion = articlePostion;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public int getArticlePostion() {
        return articlePostion;
    }

    public void setArticlePostion(int articlePostion) {
        this.articlePostion = articlePostion;
    }

}
