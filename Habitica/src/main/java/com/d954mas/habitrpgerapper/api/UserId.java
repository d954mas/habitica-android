package com.d954mas.habitrpgerapper.api;


import android.support.annotation.Nullable;

public class UserId {
    @Nullable private String api;
    @Nullable private String user;

    @Nullable
    public String getApi() {
        return api;
    }

    public void setApi(@Nullable String api) {
        this.api = api;
    }

    @Nullable
    public String getUser() {
        return user;
    }

    public void setUser(@Nullable String user) {
        this.user = user;
    }
}
