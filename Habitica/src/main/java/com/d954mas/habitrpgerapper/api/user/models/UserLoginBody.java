package com.d954mas.habitrpgerapper.api.user.models;


import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserLoginBody {
    public abstract String username();

    public abstract String password();

    public static UserLoginBody create(String username, String password) {
        return new AutoValue_UserLoginBody(username, password);
    }

}
