package com.habitrpg.android.habitica.debug.iface;

import android.app.Activity;
import android.view.ViewGroup;

public interface ViewContainer {
    ViewGroup forActivity(Activity activity);

    void initDebugView(Activity activity);
}
