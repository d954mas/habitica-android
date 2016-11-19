package com.habitrpg.android.habitica.proxy;


import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.habitrpg.android.habitica.BuildConfig;
import com.habitrpg.android.habitica.debug.iface.CrashlyticsProxy;

import android.content.Context;
import android.support.annotation.NonNull;

import io.fabric.sdk.android.Fabric;

public class CrashlyticsProxyImpl implements CrashlyticsProxy {
    private Context context;

    public CrashlyticsProxyImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        Crashlytics crashlytics = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        Fabric.with(context, crashlytics);
    }


    @Override
    public void logException(Throwable t) {
        Crashlytics.logException(t);
    }

    @Override
    public void setString(String key, String value) {
        Crashlytics.setString(key, value);
    }

    @Override
    public void setUserIdentifier(String identifier) {
        Crashlytics.getInstance().core.setUserIdentifier(identifier);
    }

    @Override
    public void setUserName(String name) {
        Crashlytics.getInstance().core.setUserName(name);
    }

    @Override
    public void fabricLogE(String s1, String s2, Exception e) {
        Fabric.getLogger().e(s1,s2,e);
    }
}
