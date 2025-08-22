package com.gtalent.tutor.responses;

public class RegisterUserResponse {
    private String username;
    private String email;
    private String password;


    public RegisterUserResponse(){

    }

    public RegisterUserResponse(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public RegisterUserResponse(String username, String email) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
