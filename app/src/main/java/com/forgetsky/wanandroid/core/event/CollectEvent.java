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
