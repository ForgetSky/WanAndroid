package com.forgetsky.wanandroid.core.event;

/**
 * Created by ForgetSky on 2019/3/30.
 */
public class RefreshTodoEvent {
    public int getStatus() {
        return status;
    }

    private int status;

    public RefreshTodoEvent(int status) {
        this.status = status;
    }

}
