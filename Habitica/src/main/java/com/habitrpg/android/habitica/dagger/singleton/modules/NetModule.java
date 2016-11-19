package com.habitrpg.android.habitica.dagger.singleton.modules;

import com.d954mas.habitrpgerapper.api.UserId;
import com.habitrpg.android.habitica.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

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
            try {
                JSONObject jsonObject = new JSONObject(stringJson);
                if (jsonObject.has("data")) dataString = jsonObject.getString("data");
            } catch (JSONException e) {
                Timber.e(e);
            }
            if (dataString == null) {
                return response;
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
