package com.habitrpg.android.habitica.dagger.singleton.modules;


import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class NetModule {
    private static final int CACHE_SIZE = 100 * 1024 * 1024;
    private static final String PICASSO_CACHE = "picasso-cache";

    @Provides @Singleton
    protected OkHttpClient providesOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides @Singleton
    Cache providesCache(Context context) {
        return new Cache(defaultCacheDir(context), CACHE_SIZE);
    }

    // @SuppressFBWarnings("RV")
    private File defaultCacheDir(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), PICASSO_CACHE);
        if (!cache.exists()) {
            //noinspection ResultOfMethodCallIgnored
            cache.mkdirs();
        }
        return cache;
    }


}
