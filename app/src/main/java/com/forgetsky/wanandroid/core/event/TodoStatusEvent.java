package com.forgetsky.wanandroid.core.event;

/**
 * Created by ForgetSky on 2019/3/30.
 */
public class TodoStatusEvent {
    public int getStatus() {
        return status;
    }

    private int status;

    public TodoStatusEvent(int status) {
        this.status = status;
    }

}
