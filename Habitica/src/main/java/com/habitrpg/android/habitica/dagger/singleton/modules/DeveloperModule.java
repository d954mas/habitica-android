package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.habitrpg.android.habitica.debug.iface.BlockCanaryProxy;
import com.habitrpg.android.habitica.debug.iface.CrashlyticsProxy;
import com.habitrpg.android.habitica.debug.iface.DevMetricsProxy;
import com.habitrpg.android.habitica.debug.iface.LeakCanaryProxy;
import com.habitrpg.android.habitica.debug.iface.LynxProxy;
import com.habitrpg.android.habitica.debug.iface.StethoProxy;
import com.habitrpg.android.habitica.debug.iface.ViewContainer;
import com.habitrpg.android.habitica.debug.impl.EmptyBlockCanary;
import com.habitrpg.android.habitica.debug.impl.EmptyCrashlyticsProxy;
import com.habitrpg.android.habitica.debug.impl.EmptyDevMetricsProxy;
import com.habitrpg.android.habitica.debug.impl.EmptyLeakCanaryProxy;
import com.habitrpg.android.habitica.debug.impl.EmptyLynx;
import com.habitrpg.android.habitica.debug.impl.EmptyStethoProxy;
import com.habitrpg.android.habitica.debug.impl.EmptyViewContainer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DeveloperModule {
    @Provides @Singleton
    protected DevMetricsProxy provideDevMetricsProxy(@NonNull Context context) {
        return new EmptyDevMetricsProxy();
    }

    @Provides @Singleton
    protected LeakCanaryProxy provideLeakCanaryProxy(@NonNull Context context) {
        return new EmptyLeakCanaryProxy();
    }

    @Provides @Singleton
    protected StethoProxy provideStethoProxy(@NonNull Context context) {
        return new EmptyStethoProxy();
    }

    @Provides @Singleton
    protected ViewContainer provideViewContainer() {
        return new EmptyViewContainer();
    }

    @Provides @Singleton
    protected BlockCanaryProxy provideBlockCanaryProxy(@NonNull Context context) {
        return new EmptyBlockCanary();
    }

    @Provides @Singleton
    protected LynxProxy provideLynxProxy(@NonNull Context context) {
        return new EmptyLynx();
    }

    @Provides @Singleton
    protected CrashlyticsProxy provideCrashlyticsProxy(@NonNull Context context) {
        return new EmptyCrashlyticsProxy();
    }

}
