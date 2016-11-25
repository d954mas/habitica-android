package com.d954mas.habitrpgerapper.api.user.models;


import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserRegisterBody {

    public abstract String username();

    public abstract String password();

    public abstract String confirmPassword();

    public abstract String email();

    public static UserRegisterBody create(String username, String password, String confirmPassword, String email) {
        return new AutoValue_UserRegisterBody(username, password, confirmPassword, email);
    }
}
