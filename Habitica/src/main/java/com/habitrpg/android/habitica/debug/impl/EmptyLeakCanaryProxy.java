package com.habitrpg.android.habitica.debug.impl;


import android.support.annotation.NonNull;

import com.habitrpg.android.habitica.debug.iface.LeakCanaryProxy;

public class EmptyLeakCanaryProxy implements LeakCanaryProxy {
    @Override
    public void init() {//pass
    }

    @Override
    public void watch(@NonNull Object object) {//pass
    }
}
