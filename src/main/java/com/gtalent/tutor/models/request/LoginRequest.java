package com.gtalent.tutor.models.request;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {
        // 空建構子給 Spring 用
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
