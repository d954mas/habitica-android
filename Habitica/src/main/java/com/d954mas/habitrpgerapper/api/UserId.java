package com.d954mas.habitrpgerapper.api;


import android.support.annotation.Nullable;

public class UserId {
    @Nullable private String api;
    @Nullable private String user;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
