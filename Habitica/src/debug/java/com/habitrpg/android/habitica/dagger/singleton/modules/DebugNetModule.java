package com.habitrpg.android.habitica.dagger.singleton.modules;

import com.d954mas.habitrpgerapper.api.UserId;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class DebugNetModule extends NetModule {

    @Override protected OkHttpClient.Builder okHttpBuilder(UserId userId) {
        OkHttpClient.Builder builder = super.okHttpBuilder(userId);
        return builder.addNetworkInterceptor(new StethoInterceptor());
    }
}
