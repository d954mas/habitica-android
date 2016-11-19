package com.habitrpg.android.habitica.debug.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.ui.fragments.BaseFragment;

public class DebugFragment extends BaseFragment {
    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.debug_fragment, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.content, new DeviceInfoFragment())
                    .add(R.id.content, new TinyDancerFragment())
                    .add(R.id.content, new ScalpelFragment())
                    .add(R.id.content, new LynxDebugFragment())
                    .commit();
        }
    }

}
