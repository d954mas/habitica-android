package com.habitrpg.android.habitica.dagger.singleton.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class DebugNetModule extends NetModule {
    @Override
    protected OkHttpClient providesOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).cache(cache).build();
    }

}
