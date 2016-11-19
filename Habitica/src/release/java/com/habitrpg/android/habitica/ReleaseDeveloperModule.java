package com.habitrpg.android.habitica;


import android.content.Context;

import com.habitrpg.android.habitica.dagger.singleton.modules.DeveloperModule;
import com.habitrpg.android.habitica.debug.iface.CrashlyticsProxy;
import com.habitrpg.android.habitica.proxy.CrashlyticsProxyImpl;

public class ReleaseDeveloperModule extends DeveloperModule {
    @Override protected CrashlyticsProxy provideCrashlyticsProxy(Context context) {
        return new CrashlyticsProxyImpl(context);
    }
}
