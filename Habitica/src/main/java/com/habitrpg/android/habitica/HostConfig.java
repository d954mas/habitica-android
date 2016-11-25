package com.habitrpg.android.habitica;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * The configuration of the host<br />
 * Currently, the Port isn't used at all.
 *
 * @author MagicMicky
 */
public class HostConfig {
    private String address;
    private int port;
    private String api;
    private String user;

    public HostConfig(SharedPreferences sharedPreferences, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.address = BuildConfig.DEBUG ? BuildConfig.BASE_URL : context.getString(R.string.base_url);
        this.api = prefs.getString(context.getString(R.string.SP_APIToken), null);
        this.user = prefs.getString(context.getString(R.string.SP_userID), null);
    }


    public String getAddress() {
        return address;
    }


    public int getPort() {
        return port;
    }

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

