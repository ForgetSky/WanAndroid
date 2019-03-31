package com.forgetsky.wanandroid.modules.todo.bean;

/**
 * Created by ForgetSky on 2019/3/30.
 */
public class TodoTypeData {
    private int type;
    private String name;

    public TodoTypeData(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
