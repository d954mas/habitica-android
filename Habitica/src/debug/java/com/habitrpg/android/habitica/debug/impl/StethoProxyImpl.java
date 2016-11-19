package com.habitrpg.android.habitica.debug.impl;


import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.Stetho;
import com.habitrpg.android.habitica.debug.iface.StethoProxy;

public class StethoProxyImpl implements StethoProxy {
    @NonNull private final Context context;

    public StethoProxyImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        Stetho.initializeWithDefaults(context);
    }
}
