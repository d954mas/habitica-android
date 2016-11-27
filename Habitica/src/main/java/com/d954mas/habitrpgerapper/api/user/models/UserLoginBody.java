package com.d954mas.habitrpgerapper.api.user.models;


public class UserLoginBody {
    String username;
    String password;

    public UserLoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
   /* public abstract String username();

    public abstract String password();

    public static UserLoginBody create(String username, String password) {
        return new AutoValue_UserLoginBody(username, password);
    }*/

}
