package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.d954mas.habitrpgerapper.ApiHelper;
import com.d954mas.habitrpgerapper.api.UserId;
import com.d954mas.habitrpgerapper.api.challenge.ChallengeService;
import com.d954mas.habitrpgerapper.api.user.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.habitrpg.android.habitica.BuildConfig;
import com.habitrpg.android.habitica.HostConfig;
import com.magicmicky.habitrpgwrapper.lib.models.Skill;
import com.magicmicky.habitrpgwrapper.lib.utils.SkillDeserializer;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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

    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL + "api/v3/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides @Singleton
    ApiHelper provideApiHelper(UserService userService, ChallengeService challengeService) {
        return new ApiHelper(userService, challengeService);
    }

    @Provides @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        Type skillListType = new TypeToken<List<Skill>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(skillListType, new SkillDeserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        return GsonConverterFactory.create(gson);
    }

    @Provides @Singleton
    ChallengeService provideChallengeService(Retrofit retrofit) {
        return retrofit.create(ChallengeService.class);
    }

    @Provides @Singleton
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

}
