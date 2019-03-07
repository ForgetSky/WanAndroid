package com.forgetsky.wanandroid.core.event;

public class RegisterEvent {

    private String username;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public RegisterEvent(String username, String password) {
        this.username = username;
        this.password = password;

    }
}
