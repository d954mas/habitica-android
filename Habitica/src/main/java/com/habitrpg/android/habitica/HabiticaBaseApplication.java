package com.habitrpg.android.habitica;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.MvpFacade;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.habitrpg.android.habitica.dagger.singleton.components.AppComponent;
import com.habitrpg.android.habitica.ui.activities.IntroActivity;
import com.habitrpg.android.habitica.ui.activities.LoginActivity;
import com.magicmicky.habitrpgwrapper.lib.models.HabitRPGUser;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.File;
import java.lang.reflect.Field;

import javax.inject.Inject;

import dagger.Lazy;

//contains all HabiticaApplicationLogic except dagger componentInitialisation
public abstract class HabiticaBaseApplication extends Application {
    //todo remove static
    public static HabitRPGUser User;
    public static Activity currentActivity = null;
    private static AppComponent component;
    @Inject Lazy<APIHelper> lazyApiHelper;
    @Inject SharedPreferences sharedPrefs;

    public static void logout(Context context) {
        HabiticaBaseApplication application = (HabiticaBaseApplication) context.getApplicationContext();
        application.deleteDatabase(HabitDatabase.NAME);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean use_reminder = preferences.getBoolean("use_reminder", false);
        String reminder_time = preferences.getString("reminder_time", "19:00");
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean("use_reminder", use_reminder);
        editor.putString("reminder_time", reminder_time);
        editor.apply();
        application.lazyApiHelper.get().updateAuthenticationCredentials(null, null);
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean checkUserAuthentication(Context context, HostConfig hostConfig) {
        if (hostConfig == null || hostConfig.getApi() == null || hostConfig.getApi().equals("") || hostConfig.getUser() == null || hostConfig.getUser().equals("")) {
            Intent intent = new Intent(context, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return false;
        }
        return true;
    }

    public static AppComponent getComponent() {
        return component;
    }

    // region SQLite overrides

    private boolean exists(@NonNull Context context) {
        try {
            File dbFile = context.getDatabasePath(String.format("%s.db", HabitDatabase.NAME));
            return dbFile.exists();
        } catch (Exception exception) {
            Log.e("DbExists", "Database %s doesn't exist.", exception);
            return false;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        setupDagger();
        setupProxy();
        setupFlowManager();
        registerActivityLifecycleCallbacks();
        Fresco.initialize(this);
        checkIfNewVersion();
    }

    private void setupProxy() {
        component.getBlockCanaryProxy().init();
        component.getCrashlyticsProxy().init();
        component.getDevMetricsProxy().init();
        component.getLeakCanaryProxy().init();
        component.getLynxProxy().init();
        component.getStethoProxy().init();
    }

    private void checkIfNewVersion() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApplication", "couldn't get package info!");
        }

        if (info == null) {
            return;
        }

        int lastInstalledVersion = sharedPrefs.getInt("last_installed_version", 0);
        if (lastInstalledVersion < info.versionCode) {
            sharedPrefs.edit().putInt("last_installed_version", info.versionCode).apply();
            APIHelper apiHelper = this.lazyApiHelper.get();

            apiHelper.apiService.getContent(apiHelper.languageCode)
                    .compose(this.lazyApiHelper.get().configureApiCallObserver())
                    .subscribe(contentResult -> {
                    }, throwable -> {
                    });
        }

    }

    private void setupDagger() {
        component = initDagger();
        component.inject(this);
    }

    protected abstract AppComponent initDagger();

    private void setupFlowManager() {
        FlowManager.init(this);
    }

    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                HabiticaBaseApplication.currentActivity = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                HabiticaBaseApplication.currentActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (currentActivity == activity)
                    currentActivity = null;
            }
        });
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name,
                                               int mode, SQLiteDatabase.CursorFactory factory) {
        return super.openOrCreateDatabase(getDatabasePath(name).getAbsolutePath(), mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name,
                                               int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return super.openOrCreateDatabase(getDatabasePath(name).getAbsolutePath(), mode, factory, errorHandler);
    }

    // endregion

    // region IAP - Specific

    @Override
    public boolean deleteDatabase(String name) {
        if (!name.endsWith(".db")) {
            name += ".db";
        }

        FlowManager.destroy();
        reflectionHack(getApplicationContext());

        boolean deleted = super.deleteDatabase(getDatabasePath(name).getAbsolutePath());

        if (deleted) {
            Log.i("hack", "Database deleted");
        } else {
            Log.e("hack", "Database not deleted");
        }

        if (exists(getApplicationContext())) {
            Log.i("hack", "Database exists before FlowManager.init");
        } else {
            Log.i("hack", "Database does not exist before FlowManager.init");
        }

        return deleted;
    }

    // Hack for DBFlow - Not deleting Database
    // https://github.com/kaeawc/dbflow-sample-app/blob/master/app/src/main/java/io/kaeawc/flow/app/ui/MainActivityFragment.java#L201
    private void reflectionHack(@NonNull Context context) {

        try {
            Field field = FlowManager.class.getDeclaredField("mDatabaseHolder");
            field.setAccessible(true);
            field.set(null, null);
        } catch (NoSuchFieldException noSuchField) {
            Log.e("nosuchfield", "No such field exists in FlowManager", noSuchField);
        } catch (IllegalAccessException illegalAccess) {
            Log.e("illegalaccess", "Illegal access of FlowManager", illegalAccess);
        }

        FlowManager.init(context);

        if (exists(context)) {
            Log.i("Database", "Database exists after FlowManager.init with reflection hack");
        } else {
            Log.i("Database", "Database does not exist after FlowManager.init with reflection hack");
        }
    }

    @Override
    public File getDatabasePath(String name) {
        File dbFile = new File(getExternalFilesDir(null), "HabiticaDatabase/" + name);
        //Crashlytics.setString("Database File", dbFile.getAbsolutePath());
        return dbFile;
    }


    // endregion

}
