package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.preference.PreferenceManager;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.helpers.SoundFileLoader;
import com.habitrpg.android.habitica.helpers.SoundManager;
import com.habitrpg.android.habitica.helpers.TagsHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Named("UserID")
    public String providesUserID(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(application.getString(R.string.SP_userID), null);
    }

    @Provides @Singleton
    public TagsHelper providesTagsHelper() {
        return new TagsHelper();
    }

    @Provides @Singleton
    public Resources providesResources(Context context) {
        return context.getResources();
    }

    @Provides @Singleton
    public SoundFileLoader providesSoundFileLoader(){
        return new SoundFileLoader();
    }

    @Provides @Singleton
    public SoundManager providesSoundManager() {
        return new SoundManager();
    }

}
