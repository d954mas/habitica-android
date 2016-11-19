package com.habitrpg.android.habitica.debug.impl;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.dagger.singleton.components.DebugAppComponent;
import com.habitrpg.android.habitica.debug.fragments.DebugFragment;
import com.habitrpg.android.habitica.debug.iface.ViewContainer;

public class ViewContainerImpl implements ViewContainer {
    private static final String DEBUG_FRAGMENT_TAG = "DEBUG_FRAGMENT_TAG";

    @Override
    public ViewGroup forActivity(Activity activity) {
        ((DebugAppComponent) HabiticaBaseApplication.getComponent()).inject(this);
        activity.setContentView(R.layout.debug_activity_frame);
        return (ViewGroup) activity.findViewById(R.id.debug_content);
    }

    @Override public void initDebugView(Activity activity) {
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        if (fragmentActivity.getSupportFragmentManager().findFragmentByTag(DEBUG_FRAGMENT_TAG) == null) {
            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.debug_container, new DebugFragment(), DEBUG_FRAGMENT_TAG).commit();
        }
    }


}
