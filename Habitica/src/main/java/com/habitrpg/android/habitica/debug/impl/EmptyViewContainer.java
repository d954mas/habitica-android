package com.habitrpg.android.habitica.debug.impl;


import android.app.Activity;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.debug.iface.ViewContainer;

import static butterknife.ButterKnife.findById;

public class EmptyViewContainer implements ViewContainer {
    @Override
    public ViewGroup forActivity(Activity activity) {
        return findById(activity, android.R.id.content);
    }

    @Override public void initDebugView(Activity activity) {
        //pass
    }
}
