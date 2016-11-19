package com.d954mas.habitrpgerapper.api.user.models;


public class UserLoginBody {
    private String username;
    private String password;

    public UserLoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
