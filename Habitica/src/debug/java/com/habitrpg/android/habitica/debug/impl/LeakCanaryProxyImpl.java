package com.habitrpg.android.habitica.debug.impl;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.habitrpg.android.habitica.debug.iface.LeakCanaryProxy;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LeakCanaryProxyImpl implements LeakCanaryProxy {
    @NonNull private final Context context;
    @Nullable private RefWatcher refWatcher;

    public LeakCanaryProxyImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        refWatcher = LeakCanary.install((Application) context);
    }

    @Override
    public void watch(@NonNull Object object) {
        if (refWatcher != null) {
            refWatcher.watch(object);
        }
    }
}
