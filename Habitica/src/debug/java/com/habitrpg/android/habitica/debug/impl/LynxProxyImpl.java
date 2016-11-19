package com.habitrpg.android.habitica.debug.impl;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.github.pedrovgs.lynx.LynxActivity;
import com.github.pedrovgs.lynx.LynxConfig;
import com.github.pedrovgs.lynx.LynxShakeDetector;
import com.habitrpg.android.habitica.debug.iface.LynxProxy;

public class LynxProxyImpl implements LynxProxy {
    @NonNull private final Context context;

    public LynxProxyImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override public void init() {
        LynxShakeDetector lynxShakeDetector = new LynxShakeDetector(context);
        lynxShakeDetector.init();
    }

    @Override public void showLynx(Context context) {
        LynxConfig lynxConfig = new LynxConfig();
        Intent lynxActivityIntent = LynxActivity.getIntent(context, lynxConfig);
        context.startActivity(lynxActivityIntent);
    }
}
