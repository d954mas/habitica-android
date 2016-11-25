package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.d954mas.habitrpgerapper.api.UserId;
import com.habitrpg.android.habitica.APIHelper;
import com.habitrpg.android.habitica.ContentCache;
import com.habitrpg.android.habitica.HostConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides @Singleton
    HostConfig providesHostConfig(SharedPreferences sharedPreferences, Context context) {
        return new HostConfig(sharedPreferences, context);
    }

    @Provides @Singleton
    UserId provicedUserId() {
        return new UserId();
    }


    @Provides
    GsonConverterFactory providesGsonConverterFactory() {
        return APIHelper.createGsonFactory();
    }

    @Provides @Singleton
    APIHelper providesApiHelper(GsonConverterFactory gsonConverter, HostConfig hostConfig) {
        return new APIHelper(gsonConverter, hostConfig);
    }

    @Provides @Singleton
    public ContentCache providesContentCache(APIHelper helper) {
        return new ContentCache(helper);
    }
}
