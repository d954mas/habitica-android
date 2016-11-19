package com.habitrpg.android.habitica.debug.impl;


import android.content.Context;

import com.habitrpg.android.habitica.debug.iface.LynxProxy;

public class EmptyLynx implements LynxProxy {
    @Override public void init() {
        //pass
    }

    @Override public void showLynx(Context context) {
        //pass
    }
}
