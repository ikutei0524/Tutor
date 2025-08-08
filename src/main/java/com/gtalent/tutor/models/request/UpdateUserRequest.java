package com.gtalent.tutor.models.request;

public class UpdateUserRequest {
    private String username;

    public UpdateUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
