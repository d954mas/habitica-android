package com.habitrpg.android.habitica.dagger.singleton.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.habitrpg.android.habitica.debug.iface.BlockCanaryProxy;
import com.habitrpg.android.habitica.debug.iface.DevMetricsProxy;
import com.habitrpg.android.habitica.debug.iface.LeakCanaryProxy;
import com.habitrpg.android.habitica.debug.iface.LynxProxy;
import com.habitrpg.android.habitica.debug.iface.StethoProxy;
import com.habitrpg.android.habitica.debug.iface.ViewContainer;
import com.habitrpg.android.habitica.debug.impl.BlockCanaryProxyImpl;
import com.habitrpg.android.habitica.debug.impl.DevMetricsProxyImpl;
import com.habitrpg.android.habitica.debug.impl.LeakCanaryProxyImpl;
import com.habitrpg.android.habitica.debug.impl.LynxProxyImpl;
import com.habitrpg.android.habitica.debug.impl.StethoProxyImpl;
import com.habitrpg.android.habitica.debug.impl.ViewContainerImpl;

import javax.annotation.Nonnull;


public class DebugDeveloperModule extends DeveloperModule {
    @Override
    protected DevMetricsProxy provideDevMetricsProxy(@NonNull Context context) {
        return new DevMetricsProxyImpl(context);
    }

    @Override
    protected LeakCanaryProxy provideLeakCanaryProxy(@NonNull Context context) {
        return new LeakCanaryProxyImpl(context);
    }

    @Override
    protected StethoProxy provideStethoProxy(@NonNull Context context) {
        return new StethoProxyImpl(context);
    }

    @Override
    protected ViewContainer provideViewContainer() {
        return new ViewContainerImpl();
    }

    @Override
    protected BlockCanaryProxy provideBlockCanaryProxy(@Nonnull Context context) {
        return new BlockCanaryProxyImpl(context);
    }

    @Override
    protected LynxProxy provideLynxProxy(@NonNull Context context) {
        return new LynxProxyImpl(context);
    }
}
