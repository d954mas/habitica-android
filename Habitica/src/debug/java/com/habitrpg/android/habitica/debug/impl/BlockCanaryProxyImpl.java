package com.habitrpg.android.habitica.debug.impl;


import android.content.Context;
import android.support.annotation.NonNull;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.habitrpg.android.habitica.debug.iface.BlockCanaryProxy;

public class BlockCanaryProxyImpl implements BlockCanaryProxy {
    @NonNull private final Context context;

    public BlockCanaryProxyImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        BlockCanary.install(context, new BlockCanaryContext()).start();
    }
}
