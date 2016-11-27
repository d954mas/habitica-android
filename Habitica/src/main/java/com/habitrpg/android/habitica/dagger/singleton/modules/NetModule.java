package com.habitrpg.android.habitica.dagger.singleton.modules;

import com.d954mas.habitrpgerapper.api.UserId;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.habitrpg.android.habitica.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetModule {

    //region okhttp
    @Provides @Singleton
    OkHttpClient providesOkHttpClient(UserId userId) {
        return okHttpBuilder(userId).build();
    }

    private Interceptor createRemoveDataInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            String stringJson = response.body().string();
            String dataString = null;
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(stringJson, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("data")) dataString = jsonObject.get("data").getAsString();
            if (dataString == null) {
                ResponseBody body = ResponseBody.create(response.body().contentType(), stringJson);
                return response.newBuilder().body(body).build();
            } else {
                ResponseBody body = ResponseBody.create(response.body().contentType(), dataString);
                return response.newBuilder().body(body).build();
            }
        };
    }

    private Interceptor createNetInterceptor(UserId userId) {
        String userAgent = System.getProperty("http.agent");
        return chain -> {
            Request original = chain.request();
            if (userId.getUser() != null) {
                Request.Builder builder = original.newBuilder()
                        .header("x-api-key", userId.getApi())
                        .header("x-api-user", userId.getUser())
                        .header("x-client", "habitica-android");
                if (userAgent != null) {
                    builder = builder.header("user-agent", userAgent);
                }
                Request request = builder.method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            } else {
                return chain.proceed(original);
            }
        };
    }

    protected OkHttpClient.Builder okHttpBuilder(UserId userId) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(createRemoveDataInterceptor())
                .addInterceptor(createNetInterceptor(userId));
        if (BuildConfig.DEBUG) builder.addInterceptor(new HttpLoggingInterceptor());
        return builder;
    }

    //endregion
}
