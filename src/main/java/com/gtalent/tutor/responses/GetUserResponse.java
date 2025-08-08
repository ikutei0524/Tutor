package com.gtalent.tutor.responses;

import com.gtalent.tutor.models.User;

public class GetUserResponse {
    private int id;
    private String username;

    public GetUserResponse(String username, int id) {
        this.id = id;
        this.username = username;
    }

    public GetUserResponse() {
    }

    public String getUsername() {
        return username;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public GetUserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
