package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.d954mas.habitrpgerapper.api.UserId;
import com.habitrpg.android.habitica.APIHelperOld;
import com.habitrpg.android.habitica.ContentCache;
import com.habitrpg.android.habitica.HostConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModuleOld {

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
        return APIHelperOld.createGsonFactory();
    }

    @Provides @Singleton
    APIHelperOld providesApiHelper(GsonConverterFactory gsonConverter, HostConfig hostConfig) {
        return new APIHelperOld(gsonConverter, hostConfig);
    }

    @Provides @Singleton
    ContentCache providesContentCache(APIHelperOld helper) {
        return new ContentCache(helper);
    }

}
