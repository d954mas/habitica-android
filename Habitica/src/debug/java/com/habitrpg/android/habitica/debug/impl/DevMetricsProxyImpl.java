package com.habitrpg.android.habitica.debug.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.habitrpg.android.habitica.debug.iface.DevMetricsProxy;


public class DevMetricsProxyImpl implements DevMetricsProxy {
    @NonNull private final Context context;

    public DevMetricsProxyImpl(@NonNull final Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        AndroidDevMetrics.initWith(context);
    }
}
